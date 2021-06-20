package com.ruoyi.ufo.operation.preview.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.ufo.autoconfiguration.UFOAutoConfiguration;
import com.ruoyi.ufo.domain.AliyunOSS;
import com.ruoyi.ufo.operation.preview.Previewer;
import com.ruoyi.ufo.operation.preview.domain.PreviewFile;
import com.ruoyi.ufo.operation.download.domain.DownloadFile;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AliyunOSSPreviewer extends Previewer {
    @Override
    public void imageThumbnailPreview(HttpServletResponse httpServletResponse, PreviewFile previewFile) {
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];
        //https://qiwen-file-free.oss-cn-zhangjiakou.aliyuncs.com/upload/20210416/56611618584472857/%E5%86%AF%E6%8F%90%E8%8E%AB%20(1).jpg?x-oss-process=image/resize,m_fill,h_250,w_250/rotate,0
        StringBuffer url = new StringBuffer();
        url.append("https://");
        url.append(UFOAutoConfiguration.aliyunConfig.getOss().getBucketName());
        url.append(".");
        url.append(UFOAutoConfiguration.aliyunConfig.getOss().getEndpoint());
        url.append(previewFile.getFileUrl());

        Map<String, Object> param = new HashMap<>();
        int thumbImageWidth = UFOAutoConfiguration.thumbImageWidth;
        int thumbImageHeight = UFOAutoConfiguration.thumbImageHeight;
        int width = thumbImageWidth == 0 ? 150 : thumbImageWidth;
        int height = thumbImageHeight == 0 ? 150 : thumbImageHeight;
        param.put("x-oss-process", "image/resize,m_fill,h_"+height+",w_"+width+"/rotate,0");
        InputStream inputStream = null;
        try {
            URL url1 = new URL(url.toString());
            URI uri = new URI(url1.getProtocol(), url1.getUserInfo(), url1.getHost(), url1.getPort(), url1.getPath(), url1.getQuery(), url1.getRef());
            String urlStr = uri.toASCIIString();

            inputStream = HttpUtils.doGet(urlStr , param);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
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
    }

    @Override
    public void imageOriginalPreview(HttpServletResponse httpServletResponse, PreviewFile previewFile) {
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];

        OSS ossClient = createOSSClient(UFOAutoConfiguration.aliyunConfig.getOss());
        OSSObject ossObject = ossClient.getObject(UFOAutoConfiguration.aliyunConfig.getOss().getBucketName(),
                previewFile.getFileUrl().substring(1));
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
