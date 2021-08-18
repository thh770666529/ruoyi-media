package com.ruoyi.file.controller;


import cn.hutool.http.HttpUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.file.NotLoginException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.file.component.FileDealComp;
import com.ruoyi.file.domain.*;
import com.ruoyi.file.dto.DownloadFileDTO;
import com.ruoyi.file.dto.PreviewDTO;
import com.ruoyi.file.dto.UploadFileDTO;
import com.ruoyi.file.service.*;
import com.ruoyi.file.vo.FileListVo;
import com.ruoyi.file.vo.UploadFileVo;
import com.ruoyi.system.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.CharsetNames;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author King
 * @Desc 该接口为文件传输接口
 */
@Slf4j
@RestController
@RequestMapping("/filetransfer")
public class FiletransferController extends BaseController {

    @Resource
    IFiletransferService filetransferService;

    @Resource
    IFileService fileService;

    @Resource
    IUserFileService userFileService;

    @Resource
    FileDealComp fileDealComp;

    @Resource
    IStorageService storageService;

    @Resource
    IShareService shareService;

    @Resource
    IShareFileService shareFileService;

    /**
     * 校验文件MD5判断文件是否存在，如果存在直接上传成功并返回skipUpload=true，如果不存在返回skipUpload=false需要再次调用该接口的POST方法
     */
    @GetMapping("/uploadfile")
    @Log(title = "极速上传,校验文件MD5判断文件是否存在", businessType = BusinessType.File)
    public AjaxResult uploadFileSpeed(UploadFileDTO uploadFileDto) {
        boolean isCheckSuccess = storageService.checkStorage(getUserId(), uploadFileDto.getTotalSize());
        if (!isCheckSuccess) {
            return AjaxResult.error("存储空间不足");
        }

        UploadFileVo uploadFileVo = new UploadFileVo();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("identifier", uploadFileDto.getIdentifier());
        synchronized (FiletransferController.class) {
            List<FileBean> list = fileService.listByMap(param);
            if (list != null && !list.isEmpty()) {
                FileBean file = list.get(0);

                UserFile userFile = new UserFile();
                userFile.setUserId(getUserId());
                userFile.setFilePath(uploadFileDto.getFilePath());
                String fileName = uploadFileDto.getFilename();
                userFile.setFileName(FileUtil.getFileNameNotExtend(fileName));
                userFile.setExtendName(FileUtil.getFileExtendName(fileName));
                List<FileListVo> userFileList = userFileService.userFileList(userFile, null, null);
                if (userFileList.size() <= 0) {

                    userFile.setIsDir(0);
                    userFile.setUploadTime(DateUtils.getTime());
                    userFile.setFileId(file.getFileId());
                    //"fileName", "filePath", "extendName", "deleteFlag", "userId"
                    userFileService.save(userFile);
                    fileService.increaseFilePointCount(file.getFileId());
                    fileDealComp.uploadESByUserFileId(userFile.getUserFileId());
                }
                uploadFileVo.setSkipUpload(true);
            } else {
                uploadFileVo.setSkipUpload(false);

            }
        }
        return AjaxResult.success(uploadFileVo);
    }


    @PostMapping("/uploadfile")
    @Log(title = "上传文件", businessType = BusinessType.File)
    public AjaxResult uploadFile(HttpServletRequest request, UploadFileDTO uploadFileDto) {
        filetransferService.uploadFile(request, uploadFileDto, getUserId());
        UploadFileVo uploadFileVo = new UploadFileVo();
        return AjaxResult.success(uploadFileVo);

    }


    @Log(title = "下载文件", businessType = BusinessType.File)
    @GetMapping(value = "/downloadfile")
    public void downloadFile(HttpServletResponse httpServletResponse, DownloadFileDTO downloadFileDTO,  String token) {
        httpServletResponse.setContentType("application/force-download");// 设置强制下载不打开
        UserFile userFile = userFileService.getById(downloadFileDTO.getUserFileId());
        String fileName = "";
        if (userFile.getIsDir() == 1) {
            fileName = userFile.getFileName() + ".zip";
        } else {
            fileName = userFile.getFileName() + "." + userFile.getExtendName();

        }
        try {
            fileName = new String(fileName.getBytes(CharsetNames.UTF_8), CharsetNames.ISO_8859_1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpServletResponse.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        filetransferService.downloadFile(httpServletResponse, downloadFileDTO);
    }

    @Log(title="预览文件", businessType = BusinessType.File)
    @GetMapping("/preview")
    public void preview(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,  PreviewDTO previewDTO){

        UserFile userFile = userFileService.getById(previewDTO.getUserFileId());
        String token = previewDTO.getToken();
        if ("undefined".equals(previewDTO.getShareBatchNum())  || StringUtils.isEmpty(previewDTO.getShareBatchNum())) {
            LoginUser loginUser = getLoginUser();
            if (loginUser == null) {
                return;
            }
            if (!userFile.getUserId().equals(loginUser.getUserId()) ) {
                return;
            }
        } else  {
            Map<String, Object> param = new HashMap<>();
            param.put("shareBatchNum", previewDTO.getShareBatchNum());
            List<Share> shareList = shareService.listByMap(param);
            //判断批次号
            if (shareList.size() <= 0) {
                return;
            }
            Integer shareType = shareList.get(0).getShareType();
            if (1 == shareType) {
                //判断提取码
                String extractionCode = shareList.get(0).getExtractionCode();
                if (!extractionCode.equals(previewDTO.getExtractionCode())) {
                    return;
                }
            }
            param.put("userFileId", previewDTO.getUserFileId());
            List<ShareFile> shareFileList = shareFileService.listByMap(param);
            if (shareFileList.size() <= 0) {
                return;
            }
        }
        FileBean fileBean = fileService.getById(userFile.getFileId());
        String mime= MimeTypeUtils.getMime(userFile.getExtendName());
        httpServletResponse.setHeader("Content-Type", mime);
        String rangeString = httpServletRequest.getHeader("Range");//如果是video标签发起的请求就不会为null
        if (StringUtils.isNotEmpty(rangeString)) {
            long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
            httpServletResponse.setContentLength(Math.toIntExact(fileBean.getFileSize()));
            httpServletResponse.setHeader("Content-Range", String.valueOf(range + (Math.toIntExact(fileBean.getFileSize()) - 1)));
        }
        httpServletResponse.setHeader("Accept-Ranges", "bytes");

        String fileName = userFile.getFileName() + "." + userFile.getExtendName();
        try {

            fileName = new String(fileName.getBytes(CharsetNames.UTF_8), CharsetNames.ISO_8859_1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        httpServletResponse.addHeader("Content-Disposition", "fileName=" + fileName);// 设置文件名
        try {
            filetransferService.previewFile(httpServletResponse, previewDTO);
        }catch (Exception e){
            //org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。
            e.printStackTrace();
            log.error("该异常忽略不做处理：" + e);
        }

    }


    @Log(title="获取存储信息", businessType = BusinessType.File)
    @GetMapping("/getstorage")
    public AjaxResult getStorage() {
        LoginUser loginUser = getLoginUser();
        StorageBean storageBean = new StorageBean();
        storageBean.setUserId(loginUser.getUserId());
        Long storageSize = filetransferService.selectStorageSizeByUserId(loginUser.getUserId());
        StorageBean storage = new StorageBean();
        storage.setUserId(loginUser.getUserId());
        storage.setStorageSize(storageSize);
        Long totalStorageSize = storageService.getTotalStorageSize(loginUser.getUserId());
        storage.setTotalStorageSize(totalStorageSize);
        return AjaxResult.success(storage);
    }


}
