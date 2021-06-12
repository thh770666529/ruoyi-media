package com.ruoyi.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.vo.FileListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFileMapper extends BaseMapper<UserFile> {
    void replaceFilePath(@Param("filePath") String filePath, @Param("oldFilePath") String oldFilePath, @Param("userId") Long userId);

    List<FileListVo> userFileList(@Param("userFile") UserFile userFile, @Param("beginCount")Long beginCount,@Param("pageCount") Long pageCount);

    void updateFilepathByPathAndName(@Param("oldfilePath") String oldfilePath,@Param("newfilePath") String newfilePath,@Param("fileName") String fileName,@Param("extendName") String extendName,@Param("userId") long userId);

    void updateFilepathByFilepath(@Param("oldfilePath") String oldfilePath,@Param("newfilePath") String newfilePath,@Param("userId") long userId);

    List<FileListVo> selectFileByExtendName(@Param("fileNameList") List<String> fileNameList,@Param("beginCount") Long beginCount,@Param("pageCount") Long pageCount,@Param("userId") long userId);

    Long selectCountByExtendName(@Param("fileNameList") List<String> fileNameList,@Param("beginCount") Long beginCount,@Param("pageCount") Long pageCount,@Param("userId") long userId);

    List<FileListVo> selectFileNotInExtendNames(@Param("fileNameList") List<String> fileNameList,@Param("beginCount") Long beginCount,@Param("pageCount") Long pageCount,@Param("userId") long userId);

    Long selectCountNotInExtendNames(@Param("fileNameList") List<String> fileNameList,@Param("beginCount") Long beginCount, @Param("pageCount") Long pageCount,@Param("userId") long userId);

    Long selectStorageSizeByUserId(@Param("userId") Long userId);
}
