package com.ruoyi.ufo.operation.read.product;

import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.ufo.exception.ReadException;
import com.ruoyi.ufo.operation.read.Reader;
import com.ruoyi.ufo.operation.read.domain.ReadFile;
import com.ruoyi.ufo.util.PathUtil;
import com.ruoyi.ufo.util.ReadFileUtils;

import java.io.IOException;

public class LocalStorageReader extends Reader {
    @Override
    public String read(ReadFile readFile) {

        String fileContent;
        try {
            fileContent = ReadFileUtils.getContentByPath(PathUtil.getStaticPath() + readFile.getFileUrl());
        } catch (IOException e) {
            throw new ReadException("文件读取出现异常", e);
        }
        return fileContent;
    }
}
