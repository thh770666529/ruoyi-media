package com.ruoyi.ufo.operation.delete.product;

import com.ruoyi.common.operation.FileOperation;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.ufo.operation.delete.Deleter;
import com.ruoyi.ufo.operation.delete.domain.DeleteFile;
import com.ruoyi.ufo.util.PathUtil;
import org.springframework.stereotype.Component;

@Component
public class LocalStorageDeleter extends Deleter {
    @Override
    public void delete(DeleteFile deleteFile) {
        FileOperation.deleteFile(PathUtil.getStaticPath() + deleteFile.getFileUrl());
        if (FileUtil.isImageFile(FileUtil.getFileExtendName(deleteFile.getFileUrl()))) {
            FileOperation.deleteFile(PathUtil.getStaticPath() + deleteFile.getFileUrl().replace(deleteFile.getTimeStampName(), deleteFile.getTimeStampName() + "_min"));
        }
    }
}
