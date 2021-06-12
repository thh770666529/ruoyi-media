package com.ruoyi.ufo.operation.download.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.ruoyi.ufo.autoconfiguration.UFOAutoConfiguration;
import com.ruoyi.ufo.domain.AliyunOSS;
import com.ruoyi.ufo.operation.download.Downloader;
import com.ruoyi.ufo.operation.download.domain.DownloadFile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class AliyunOSSDownloader extends Downloader {

    @Override
    public void download(HttpServletResponse httpServletResponse, DownloadFile downloadFile) {

        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];

        OSS ossClient = createOSSClient(UFOAutoConfiguration.aliyunConfig.getOss());
        OSSObject ossObject = ossClient.getObject(UFOAutoConfiguration.aliyunConfig.getOss().getBucketName(),
                downloadFile.getFileUrl().substring(1));
        InputStream inputStream = ossObject.getObjectContent();
        try {
            bis = new BufferedInputStream(inputStream);
            OutputStream os = httpServletResponse.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        ossClient.shutdown();
    }

    @Override
    public InputStream getInputStream(DownloadFile downloadFile) {
        OSS ossClient = createOSSClient(UFOAutoConfiguration.aliyunConfig.getOss());
        OSSObject ossObject = ossClient.getObject(UFOAutoConfiguration.aliyunConfig.getOss().getBucketName(),
                downloadFile.getFileUrl().substring(1));
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
