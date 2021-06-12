package com.ruoyi.ufo.operation.read.product;

import com.ruoyi.common.operation.FileOperation;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.ufo.exception.ReadException;
import com.ruoyi.ufo.operation.read.Reader;
import com.ruoyi.ufo.operation.read.domain.ReadFile;
import com.ruoyi.ufo.util.PathUtil;
import com.ruoyi.ufo.util.ReadFileUtils;

import java.io.*;

public class FastDFSReader extends Reader {
    @Override
    public String read(ReadFile readFile) {
//        String extendName = FileUtil.getFileExtendName(readFile.getFileUrl());
//        return null;

        String fileUrl = readFile.getFileUrl();
        String fileType = FileUtil.getFileExtendName(fileUrl);
        try {
            return ReadFileUtils.getContentByInputStream(fileType, getInputStream(readFile.getFileUrl()));
        } catch (IOException e) {
            throw new ReadException("读取文件失败", e);
        }
    }

    public InputStream getInputStream(String fileUrl) {
        //设置文件路径
        File file = FileOperation.newFile(PathUtil.getStaticPath() + fileUrl);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;

    }
}
