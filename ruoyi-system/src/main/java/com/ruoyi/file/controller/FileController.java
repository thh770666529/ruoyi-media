package com.ruoyi.file.controller;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.file.NotLoginException;
import com.ruoyi.common.operation.FileOperation;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.file.component.FileDealComp;
import com.ruoyi.file.domain.FileBean;
import com.ruoyi.file.domain.TreeNode;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.dto.*;
import com.ruoyi.file.service.IFileService;
import com.ruoyi.file.service.IUserFileService;
import com.ruoyi.file.vo.FileListVo;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.util.TokenUtil;
import com.ruoyi.ufo.factory.UFOFactory;
import com.ruoyi.ufo.operation.rename.domain.RenameFile;
import com.ruoyi.ufo.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.ruoyi.common.utils.file.qiwen.FileUtil.getFileExtendsByType;


/**
 * 文件接口  做逻辑删除 移动 存储
 *
 * @author thh
 * @date 2021-06-05
 */
@RestController
@Slf4j
@RequestMapping("/file")
public class FileController extends BaseController {
    @Autowired
    IFileService fileService;

    @Autowired
    ISysUserService sysUserService;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    IUserFileService userFileService;

    @Resource
    FileDealComp fileDealComp;

    @Resource
    UFOFactory ufoFactory;


    public static Executor executor = Executors.newFixedThreadPool(20);

    @Log(title = "创建文件", businessType = BusinessType.File)
    @PostMapping("/createfile")
    public AjaxResult createFile(@RequestBody CreateFileDTO createFileDto) {

        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        List<UserFile> userFiles = userFileService.selectUserFileByNameAndPath(createFileDto.getFileName(), createFileDto.getFilePath(), loginUser.getUserId());
        if (userFiles != null && !userFiles.isEmpty()) {
            return error(("同名文件已存在"));
        }

        UserFile userFile = new UserFile();
        userFile.setUserId(loginUser.getUserId());
        userFile.setFileName(createFileDto.getFileName());
        userFile.setFilePath(createFileDto.getFilePath());
        userFile.setDeleteFlag(0);
        userFile.setIsDir(1);
        userFile.setUploadTime(DateUtils.getTime());

        userFileService.save(userFile);
        //fileDealComp.uploadESByUserFileId(userFile.getUserFileId());
        return success();
    }


    @Log(title = "文件重命名", businessType = BusinessType.File)
    @PostMapping("/renamefile")
    public AjaxResult renameFile(@RequestBody RenameFileDTO renameFileDto) {

        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        UserFile userFile = userFileService.getById(renameFileDto.getUserFileId());

        List<UserFile> userFiles = userFileService.selectUserFileByNameAndPath(renameFileDto.getFileName(), renameFileDto.getFilePath(), loginUser.getUserId());
        if (userFiles != null && !userFiles.isEmpty()) {
            return error("同名文件已存在");
        }

        if (1 == userFile.getIsDir()) {
            LambdaUpdateWrapper<UserFile> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.set(UserFile::getFileName, renameFileDto.getFileName())
                    .set(UserFile::getUploadTime, DateUtils.getTime())
                    .eq(UserFile::getUserFileId, renameFileDto.getUserFileId());
            userFileService.update(lambdaUpdateWrapper);
            userFileService.replaceUserFilePath(userFile.getFilePath() + renameFileDto.getFileName() + "/",
                    userFile.getFilePath() + userFile.getFileName() + "/", loginUser.getUserId());
        } else {
            FileBean file = fileService.getById(userFile.getFileId());
            if (file.getStorageType() == 1) {

                String fileUrl = file.getFileUrl();
                String newFileUrl = fileUrl.replace(userFile.getFileName(), renameFileDto.getFileName());
                RenameFile renameFile = new RenameFile();
                renameFile.setSrcName(fileUrl.substring(1));
                renameFile.setDestName(newFileUrl.substring(1));
                ufoFactory.getRenamer(file.getStorageType()).rename(renameFile);
                LambdaUpdateWrapper<FileBean> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper
                        .set(FileBean::getFileUrl, newFileUrl)
                        .eq(FileBean::getFileId, file.getFileId());
                fileService.update(lambdaUpdateWrapper);

                LambdaUpdateWrapper<UserFile> userFileLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                userFileLambdaUpdateWrapper
                        .set(UserFile::getFileName, renameFileDto.getFileName())
                        .set(UserFile::getUploadTime, DateUtils.getTime())
                        .eq(UserFile::getUserFileId, renameFileDto.getUserFileId());
                userFileService.update(userFileLambdaUpdateWrapper);
            } else {
                LambdaUpdateWrapper<UserFile> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.set(UserFile::getFileName, renameFileDto.getFileName())
                        .set(UserFile::getUploadTime, DateUtils.getTime())
                        .eq(UserFile::getUserFileId, renameFileDto.getUserFileId());
                userFileService.update(lambdaUpdateWrapper);
            }

        }
        fileDealComp.uploadESByUserFileId(renameFileDto.getUserFileId());
        return success();
    }


    @Log(title = "批量删除文件", businessType = BusinessType.File)
    @PostMapping("/batchdeletefile")
    public AjaxResult deleteImageByIds(@RequestBody BatchDeleteFileDTO batchDeleteFileDto) {

        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());

        List<UserFile> userFiles = JSON.parseArray(batchDeleteFileDto.getFiles(), UserFile.class);
        // DigestUtils.md5Hex("data");
        for (UserFile userFile : userFiles) {
            //userFile.setDeleteBatchNum(uuid);
            userFileService.deleteUserFile(userFile.getUserFileId(), loginUser.getUserId());
            fileDealComp.deleteESByUserFileId(userFile.getUserFileId());
        }
        return success("批量删除文件成功");
    }


    @Log(title = "可以删除文件或者目录", businessType = BusinessType.File)
    @PostMapping("/deletefile")
    public AjaxResult deleteFile(@RequestBody DeleteFileDTO deleteFileDto) {

        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        userFileService.deleteUserFile(deleteFileDto.getUserFileId(), loginUser.getUserId());
        fileDealComp.deleteESByUserFileId(deleteFileDto.getUserFileId());
        return success();

    }


    @Log(title = "解压文件", businessType = BusinessType.File)
    @PostMapping("/unzipfile")
    public AjaxResult unzipFile(@RequestBody UnzipFileDTO unzipFileDto) {

        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        String zipFileUrl = PathUtil.getStaticPath() + unzipFileDto.getFileUrl();
        File file = FileOperation.newFile(zipFileUrl);
        String extendName = FileUtil.getFileExtendName(zipFileUrl);
        String unzipUrl = zipFileUrl.replace("." + extendName, "");
        String[] arr = unzipFileDto.getFileUrl().split("\\.");
        if (arr.length <= 1) {
            return error("文件名格式错误！");
        }
        List<String> fileEntryNameList = new ArrayList<>();
        if ("zip".equals(arr[1])) {
            fileEntryNameList = FileOperation.unzip(file, unzipUrl);
        } else if ("rar".equals(arr[1])) {
            try {
                fileEntryNameList = FileOperation.unrar(file, unzipUrl);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("rar解压失败" + e);
                return error("rar解压失败！");


            }
        } else {
            return error("不支持的文件格式！");
        }

        List<FileBean> fileBeanList = new ArrayList<>();
        for (int i = 0; i < fileEntryNameList.size(); i++) {
            String entryName = fileEntryNameList.get(i);
            log.info("文件名：" + entryName);
            executor.execute(() -> {
                String totalFileUrl = unzipUrl + entryName;
                File currentFile = FileOperation.newFile(totalFileUrl);

                FileBean tempFileBean = new FileBean();
                UserFile userFile = new UserFile();

                userFile.setUploadTime(DateUtils.getTime());
                userFile.setUserId(loginUser.getUserId());
                userFile.setFilePath(FileUtil.pathSplitFormat(unzipFileDto.getFilePath() + entryName.replace(currentFile.getName(), "")).replace("\\", "/"));

                if (currentFile.isDirectory()) {

                    userFile.setIsDir(1);

                    userFile.setFileName(currentFile.getName());
                    tempFileBean.setTimeStampName(currentFile.getName());
                } else {

                    userFile.setIsDir(0);
                    userFile.setExtendName(FileUtil.getFileExtendName(totalFileUrl));
                    userFile.setFileName(FileUtil.getFileNameNotExtend(currentFile.getName()));
                    tempFileBean.setFileSize(currentFile.length());
                    tempFileBean.setTimeStampName(FileUtil.getFileNameNotExtend(currentFile.getName()));
                    tempFileBean.setFileUrl(File.separator + (currentFile.getPath()).replace(PathUtil.getStaticPath(), ""));
                    tempFileBean.setPointCount(1);
                    tempFileBean.setStorageType(0);
                    fileService.save(tempFileBean);
                }

                userFile.setFileId(tempFileBean.getFileId());
                userFile.setDeleteFlag(0);
                userFileService.save(userFile);
            });

        }
        return success();

    }


    @Log(title = "文件或者目录移动", businessType = BusinessType.File)
    @PostMapping("/movefile")
    public AjaxResult moveFile(@RequestBody MoveFileDTO moveFileDto) {

        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        String oldfilePath = moveFileDto.getOldFilePath();
        String newfilePath = moveFileDto.getFilePath();
        String fileName = moveFileDto.getFileName();
        String extendName = moveFileDto.getExtendName();
        userFileService.updateFilepathByFilepath(oldfilePath, newfilePath, fileName, extendName, loginUser.getUserId());
        return success();

    }


    @Log(title = "批量移动文件", businessType = BusinessType.File)
    @PostMapping("/batchmovefile")
    public AjaxResult batchMoveFile(@RequestBody BatchMoveFileDTO batchMoveFileDto) {

        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        String files = batchMoveFileDto.getFiles();
        String newfilePath = batchMoveFileDto.getFilePath();
        List<UserFile> fileList = JSON.parseArray(files, UserFile.class);
        for (UserFile userFile : fileList) {
            userFileService.updateFilepathByFilepath(userFile.getFilePath(), newfilePath, userFile.getFileName(), userFile.getExtendName(), loginUser.getUserId());
        }
        return success("批量移动文件成功");

    }


    @Log(title = "通过文件类型选择文件", businessType = BusinessType.File)
    @GetMapping("/selectfilebyfiletype")
    public AjaxResult selectFileByFileType(int fileType, Long currentPage, Long pageCount) {

        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        long userId = loginUser.getUserId();
        //startPage();
        List<FileListVo> fileList = new ArrayList<>();
        Long beginCount = 0L;
        Long total = 0L;

        if (pageCount == null || currentPage == null) {
            beginCount = 0L;
            pageCount = 10L;
        } else {
            beginCount = (currentPage - 1) * pageCount;
        }
        if (fileType == FileUtil.OTHER_TYPE) {
            List<String> arrList = new ArrayList<>();
            arrList.addAll(Arrays.asList(FileUtil.DOC_FILE));
            arrList.addAll(Arrays.asList(FileUtil.IMG_FILE));
            arrList.addAll(Arrays.asList(FileUtil.VIDEO_FILE));
            arrList.addAll(Arrays.asList(FileUtil.MUSIC_FILE));
            fileList = userFileService.selectFileNotInExtendNames(arrList, beginCount, pageCount, userId);
            total = userFileService.selectCountNotInExtendNames(arrList, beginCount, pageCount, userId);
        } else {
            fileList = userFileService.selectFileByExtendName(getFileExtendsByType(fileType), beginCount, pageCount, userId);
            total = userFileService.selectCountByExtendName(getFileExtendsByType(fileType), beginCount, pageCount, userId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", fileList);
        return success(map);

    }


    @Log(title = "获取文件树", businessType = BusinessType.File)
    @GetMapping("/getfiletree")
    public AjaxResult getFileTree() {
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        long userId = loginUser.getUserId();

        List<UserFile> userFileList = userFileService.selectFilePathTreeByUserId(loginUser.getUserId());
        TreeNode resultTreeNode = new TreeNode();
        resultTreeNode.setLabel("/");
        resultTreeNode.setId(0L);
        long id = 1;
        for (int i = 0; i < userFileList.size(); i++) {
            UserFile userFile = userFileList.get(i);
            String filePath = userFile.getFilePath() + userFile.getFileName() + "/";

            Queue<String> queue = new LinkedList<>();

            String[] strArr = filePath.split("/");
            for (int j = 0; j < strArr.length; j++) {
                if (!"".equals(strArr[j]) && strArr[j] != null) {
                    queue.add(strArr[j]);
                }

            }
            if (queue.size() == 0) {
                continue;
            }

            resultTreeNode = fileDealComp.insertTreeNode(resultTreeNode, id++, "/", queue);


        }
        List<TreeNode> treeNodeList = resultTreeNode.getChildren();
        Collections.sort(treeNodeList, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                long i = o1.getId() - o2.getId();
                return (int) i;
            }
        });
        return success(resultTreeNode);

    }


    @Log(title = "获取文件列表", businessType = BusinessType.File)
    @GetMapping("/getfilelist")
    public AjaxResult getFileList(FileListDTO fileListDto) {

        UserFile userFile = new UserFile();
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        userFile.setUserId(loginUser.getUserId());
        List<FileListVo> fileList = null;
        userFile.setFilePath(PathUtil.urlDecode(fileListDto.getFilePath()));
        if (fileListDto.getCurrentPage() == null || fileListDto.getPageCount() == null) {
            fileList = userFileService.userFileList(userFile, 0L, 10L);
        } else {
            Long beginCount = (fileListDto.getCurrentPage() - 1) * fileListDto.getPageCount();
            fileList = userFileService.userFileList(userFile, beginCount, fileListDto.getPageCount()); //fileService.selectFileListByPath(fileBean);

        }
        LambdaQueryWrapper<UserFile> userFileLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userFileLambdaQueryWrapper.eq(UserFile::getUserId, userFile.getUserId())
                .eq(UserFile::getFilePath, userFile.getFilePath())
                .eq(UserFile::getDeleteFlag, 0);
        long total = userFileService.count(userFileLambdaQueryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", fileList);
        return success(map);

    }

    @Log(title = "文件搜索", businessType = BusinessType.File)
    @GetMapping("/search")
    public AjaxResult searchFile(SearchFileDTO searchFileDTO) {

        return success("文件搜索");
    }

}
