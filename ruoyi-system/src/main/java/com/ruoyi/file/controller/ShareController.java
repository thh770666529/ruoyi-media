package com.ruoyi.file.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.file.component.FileDealComp;
import com.ruoyi.file.domain.Share;
import com.ruoyi.file.domain.ShareFile;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.dto.*;
import com.ruoyi.file.service.IFileService;
import com.ruoyi.file.service.IShareFileService;
import com.ruoyi.file.service.IShareService;
import com.ruoyi.file.service.IUserFileService;
import com.ruoyi.file.vo.ShareFileListVO;
import com.ruoyi.file.vo.ShareFileVO;
import com.ruoyi.file.vo.ShareListVO;
import com.ruoyi.file.vo.ShareTypeVO;
import com.ruoyi.system.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

// "该接口为文件分享接口"
@RestController
@Slf4j
@RequestMapping("/share")
public class ShareController extends BaseController {

    public static final String CURRENT_MODULE = "文件分享";

    @Resource
    IShareFileService shareFileService;
    @Resource
    IShareService shareService;
    @Resource
    IFileService fileService;
    @Resource
    IUserFileService userFileService;
    @Resource
    FileDealComp fileDealComp;

    @PostMapping("/sharefile")
    @Log(title = "分享文件", businessType = BusinessType.File)
    public AjaxResult shareFile(@RequestBody ShareFileDTO shareSecretDTO) {
        ShareFileVO shareSecretVO = new ShareFileVO();
        LoginUser loginUser = getLoginUser();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Share share = new Share();
        BeanUtil.copyProperties(shareSecretDTO, share);
        share.setShareTime(DateUtils.getTime());
        share.setUserId(loginUser.getUserId());
        share.setShareStatus(0);
        if (shareSecretDTO.getShareType() == 1) {
            String extractionCode = RandomUtil.randomNumbers(6);
            share.setExtractionCode(extractionCode);
            shareSecretVO.setExtractionCode(share.getExtractionCode());
        }
        share.setShareBatchNum(uuid);
        shareService.save(share);
        List<ShareFile> fileList = JSON.parseArray(shareSecretDTO.getFiles(), ShareFile.class);
        List<ShareFile> saveFileList = new ArrayList<>();
        for (ShareFile shareFile : fileList) {
            UserFile userFile = userFileService.getById(shareFile.getUserFileId());
            if (userFile.getUserId().compareTo(loginUser.getUserId()) != 0) {
                return error("您只能分享自己的文件");
            }
            if (userFile.getIsDir() == 1) {
                List<UserFile> userfileList = userFileService.selectFileListLikeRightFilePath(userFile.getFilePath() + userFile.getFileName() + "/", loginUser.getUserId());
                for (UserFile userFile1 : userfileList) {
                    ShareFile shareFile1 = new ShareFile();
                    shareFile1.setUserFileId(userFile1.getUserFileId());
                    shareFile1.setShareBatchNum(uuid);
                    shareFile1.setShareFilePath(userFile1.getFilePath().replaceFirst(userFile.getFilePath(), "/"));
                    saveFileList.add(shareFile1);
                }
            }
            shareFile.setShareFilePath("/");
            shareFile.setShareBatchNum(uuid);
            saveFileList.add(shareFile);


        }
        shareFileService.batchInsertShareFile(saveFileList);
        shareSecretVO.setShareBatchNum(uuid);
        return AjaxResult.success(shareSecretVO);
    }


    @Log(title = "保存分享文件", businessType = BusinessType.File)
    @PostMapping("/savesharefile")
    @Transactional(rollbackFor=Exception.class)
    public AjaxResult saveShareFile(@RequestBody SaveShareFileDTO saveShareFileDTO) {
        List<ShareFile> fileList = JSON.parseArray(saveShareFileDTO.getFiles(), ShareFile.class);
        String savefilePath = saveShareFileDTO.getFilePath();
        Long userId = getUserId();

        List<UserFile> saveUserFileList = new ArrayList<>();
        for (ShareFile shareFile : fileList) {
            UserFile userFile = userFileService.getById(shareFile.getUserFileId());
            String fileName = userFile.getFileName();
            String savefileName = fileDealComp.getRepeatFileName(userFile, savefilePath);

            if (userFile.getIsDir() == 1) {
                List<UserFile> userfileList = userFileService.selectFileListLikeRightFilePath(userFile.getFilePath() + userFile.getFileName(), userFile.getUserId());
                log.info("查询文件列表：" + JSON.toJSONString(userfileList));
                String filePath = userFile.getFilePath();
                userfileList.forEach(p->{
                    p.setUserFileId(null);
                    p.setUserId(userId);
                    p.setFilePath(p.getFilePath().replaceFirst(filePath + fileName, savefilePath + savefileName));
                    saveUserFileList.add(p);
                    log.info("当前文件：" + JSON.toJSONString(p));
                    if (p.getIsDir() == 0) {
                        fileService.increaseFilePointCount(p.getFileId());
                    }
                });
            } else {
                fileService.increaseFilePointCount(userFile.getFileId());
            }
            userFile.setUserFileId(null);
            userFile.setUserId(userId);
            userFile.setFilePath(savefilePath);
            userFile.setFileName(savefileName);
            saveUserFileList.add(userFile);
        }
        log.info("----------" + JSON.toJSONString(saveUserFileList));
        userFileService.saveBatch(saveUserFileList);
        return success();
    }

    @Log(title = "查看已分享列表", businessType = BusinessType.File)
    @GetMapping("/getMyShareList")
    public AjaxResult getMyShareList(ShareListDTO shareListDTO) {
        LoginUser loginUser = getLoginUser();
        List<ShareListVO> shareList = shareService.selectShareList(shareListDTO, loginUser.getUserId());
        int total = shareService.selectShareListTotalCount(shareListDTO, loginUser.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", shareList);
        return AjaxResult.success(map);
    }


    @Log(title = "分享文件列表", businessType = BusinessType.File)
    @GetMapping(value = "/sharefileList")
    public AjaxResult shareFileList(ShareFileListDTO shareFileListBySecretDTO) {
        String shareBatchNum = shareFileListBySecretDTO.getShareBatchNum();
        String shareFilePath = shareFileListBySecretDTO.getShareFilePath();
        List<ShareFileListVO> list = shareFileService.selectShareFileList(shareBatchNum, shareFilePath);
        for (ShareFileListVO shareFileListVO : list) {
            shareFileListVO.setShareFilePath(shareFilePath);
        }
        return AjaxResult.success(list);
    }

    @Log(title = "分享类型", businessType = BusinessType.File)
    @GetMapping("/sharetype")
    public AjaxResult shareType(ShareTypeDTO shareTypeDTO) {
        LambdaQueryWrapper<Share> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Share::getShareBatchNum, shareTypeDTO.getShareBatchNum());
        Share share = shareService.getOne(lambdaQueryWrapper);
        ShareTypeVO shareTypeVO = new ShareTypeVO();
        shareTypeVO.setShareType(share.getShareType());
        return AjaxResult.success(shareTypeVO);
    }

    @Log(title = "校验提取码", businessType = BusinessType.File)
    @GetMapping(value = "/checkextractioncode")
    public AjaxResult checkExtractionCode(CheckExtractionCodeDTO checkExtractionCodeDTO) {
        LambdaQueryWrapper<Share> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Share::getShareBatchNum, checkExtractionCodeDTO.getShareBatchNum())
                .eq(Share::getExtractionCode, checkExtractionCodeDTO.getExtractionCode());
        List<Share> list = shareService.list(lambdaQueryWrapper);
        if (list.isEmpty()) {
            return error("校验失败");
        } else {
            return success();
        }
    }

    @Log(title = "校验过期时间", businessType = BusinessType.File)
    @GetMapping("/checkendtime")
    public AjaxResult checkEndTime(CheckEndTimeDTO checkEndTimeDTO) {
        LambdaQueryWrapper<Share> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Share::getShareBatchNum, checkEndTimeDTO.getShareBatchNum());
        Share share = shareService.getOne(lambdaQueryWrapper);
        String endTime = share.getEndTime();
        Date endTimeDate = null;
        endTimeDate = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, endTime);
        if (new Date().after(endTimeDate))  {
            return error("分享已过期");
        } else {
            return success();
        }
    }
}
