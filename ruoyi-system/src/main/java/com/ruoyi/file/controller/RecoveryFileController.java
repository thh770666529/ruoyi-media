package com.ruoyi.file.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.file.NotLoginException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.file.domain.RecoveryFile;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.dto.BatchDeleteRecoveryFileDTO;
import com.ruoyi.file.dto.DeleteRecoveryFileDTO;
import com.ruoyi.file.dto.RestoreFileDTO;
import com.ruoyi.file.service.IRecoveryFileService;
import com.ruoyi.file.service.IUserFileService;
import com.ruoyi.file.vo.RecoveryFileListVo;
import com.ruoyi.system.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

// description 文件删除后会进入回收站，该接口主要是对回收站文件进行管理
@RestController
@Slf4j
@RequestMapping("/recoveryfile")
public class RecoveryFileController extends BaseController {
    @Resource
    IRecoveryFileService recoveryFileService;
    @Resource
    IUserFileService userFileService;

    @Log(title = "删除回收文件", businessType = BusinessType.File)
    @RequestMapping(value = "/deleterecoveryfile", method = RequestMethod.POST)
    public AjaxResult deleteRecoveryFile(@RequestBody DeleteRecoveryFileDTO deleteRecoveryFileDTO) {
        RecoveryFile recoveryFile = recoveryFileService.getById(deleteRecoveryFileDTO.getRecoveryFileId());
        UserFile userFile =userFileService.getById(recoveryFile.getUserFileId());
        recoveryFileService.deleteRecoveryFile(userFile);
        recoveryFileService.removeById(deleteRecoveryFileDTO.getRecoveryFileId());
        return AjaxResult.success("删除成功");
    }


    @PostMapping(value = "/batchdelete")
    @Log(title = "批量删除回收文件", businessType = BusinessType.File)
    @ResponseBody
    public AjaxResult batchDeleteRecoveryFile(@RequestBody BatchDeleteRecoveryFileDTO batchDeleteRecoveryFileDTO) {

        List<RecoveryFile> recoveryFileList = JSON.parseArray(batchDeleteRecoveryFileDTO.getRecoveryFileIds(), RecoveryFile.class);
        for (RecoveryFile recoveryFile : recoveryFileList) {

            RecoveryFile recoveryFile1 = recoveryFileService.getById(recoveryFile.getRecoveryFileId());
            UserFile userFile =userFileService.getById(recoveryFile1.getUserFileId());

            recoveryFileService.deleteRecoveryFile(userFile);
            recoveryFileService.removeById(recoveryFile.getRecoveryFileId());
        }
        return AjaxResult.success("批量删除成功");
    }

    @Log(title = "回收文件列表", businessType = BusinessType.File)
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getRecoveryFileList() {
        LoginUser loginUser = getLoginUser();
        startPage();
        List<RecoveryFileListVo> recoveryFileList = recoveryFileService.selectRecoveryFileList(loginUser.getUserId());
        return getDataTable(recoveryFileList);
    }


    @PostMapping(value = "/restorefile")
    @Log(title = "还原文件", businessType = BusinessType.File)
    public AjaxResult restoreFile(@RequestBody RestoreFileDTO restoreFileDto) {
        LoginUser loginUser = getLoginUser();
        recoveryFileService.restorefile(restoreFileDto.getDeleteBatchNum(), restoreFileDto.getFilePath(), loginUser.getUserId());
        return AjaxResult.success("还原成功！");
    }

}









