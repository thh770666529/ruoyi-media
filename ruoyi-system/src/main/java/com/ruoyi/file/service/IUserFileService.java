package com.ruoyi.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.vo.FileListVo;

import java.util.List;

public interface IUserFileService extends IService<UserFile> {
    List<UserFile> selectUserFileByNameAndPath(String fileName, String filePath, Long userId);

    void replaceUserFilePath(String filePath, String oldFilePath, Long userId);

    List<FileListVo> userFileList(UserFile userFile, Long beginCount, Long pageCount);

    void updateFilepathByFilepath(String oldfilePath, String newfilePath, String fileName, String extendName, long userId);

    List<FileListVo> selectFileByExtendName(List<String> fileNameList, Long beginCount, Long pageCount, long userId);
    Long selectCountByExtendName(List<String> fileNameList, Long beginCount, Long pageCount, long userId);
    List<FileListVo> selectFileNotInExtendNames(List<String> fileNameList, Long beginCount, Long pageCount, long userId);
    Long selectCountNotInExtendNames(List<String> fileNameList, Long beginCount, Long pageCount, long userId);
    List<UserFile> selectFileListLikeRightFilePath(String filePath, long userId);
    List<UserFile> selectFilePathTreeByUserId(Long userId);
    void deleteUserFile(Long userFileId, Long sessionUserId);

}
