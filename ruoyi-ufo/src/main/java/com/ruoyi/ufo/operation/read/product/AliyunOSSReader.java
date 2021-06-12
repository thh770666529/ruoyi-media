package com.ruoyi.ufo.operation.read.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.ufo.autoconfiguration.UFOAutoConfiguration;
import com.ruoyi.ufo.domain.AliyunOSS;
import com.ruoyi.ufo.exception.ReadException;
import com.ruoyi.ufo.operation.read.Reader;
import com.ruoyi.ufo.operation.read.domain.ReadFile;
import com.ruoyi.ufo.util.ReadFileUtils;

import java.io.IOException;
import java.io.InputStream;

public class AliyunOSSReader extends Reader {
    @Override
    public String read(ReadFile readFile) {
        String fileUrl = readFile.getFileUrl();
        String fileType = FileUtil.getFileExtendName(fileUrl);
        try {
            return ReadFileUtils.getContentByInputStream(fileType, getInputStream(readFile.getFileUrl()));
        } catch (IOException e) {
            throw new ReadException("读取文件失败", e);
        }
    }

    public InputStream getInputStream(String fileUrl) {
        OSS ossClient = createOSSClient(UFOAutoConfiguration.aliyunConfig.getOss());
        OSSObject ossObject = ossClient.getObject(UFOAutoConfiguration.aliyunConfig.getOss().getBucketName(),
                fileUrl.substring(1));
        InputStream inputStream = ossObject.getObjectContent();
        return inputStream;
    }


    public OSS createOSSClient(AliyunOSS aliyunOSS) {
        String endpoint = aliyunOSS.getEndpoint();
        String accessKeyId = aliyunOSS.getAccessKeyId();
        String accessKeySecret = aliyunOSS.getAccessKeySecret();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }
}
