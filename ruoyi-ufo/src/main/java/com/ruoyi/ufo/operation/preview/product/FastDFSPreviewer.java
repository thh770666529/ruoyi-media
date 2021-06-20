package com.ruoyi.ufo.operation.preview.product;


import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ruoyi.common.operation.ImageOperation;
import com.ruoyi.ufo.autoconfiguration.UFOAutoConfiguration;
import com.ruoyi.ufo.operation.preview.Previewer;
import com.ruoyi.ufo.operation.preview.domain.PreviewFile;
import com.ruoyi.ufo.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Slf4j
@Component
public class FastDFSPreviewer extends Previewer {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Override
    public void imageThumbnailPreview(HttpServletResponse httpServletResponse, PreviewFile previewFile) {

        String savePath = PathUtil.getStaticPath() + "cache" + File.separator + previewFile.getFileUrl();
        File saveFile = new File(savePath);
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];
        if (saveFile.exists()) {
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(saveFile);
                bis = new BufferedInputStream(fis);
                OutputStream os = httpServletResponse.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
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
        } else {
            InputStream inputstream = getInputStream(previewFile.getFileUrl());
            InputStream in = null;
            try {
                int thumbImageWidth = UFOAutoConfiguration.thumbImageWidth;
                int thumbImageHeight = UFOAutoConfiguration.thumbImageHeight;
                int width = thumbImageWidth == 0 ? 150 : thumbImageWidth;
                int height = thumbImageHeight == 0 ? 150 : thumbImageHeight;
                in = ImageOperation.thumbnailsImage(inputstream, saveFile, width, height);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                bis = new BufferedInputStream(in);
                OutputStream os = httpServletResponse.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
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

    }

    @Override
    public void imageOriginalPreview(HttpServletResponse httpServletResponse, PreviewFile previewFile) {
        String group = previewFile.getFileUrl().substring(0, previewFile.getFileUrl().indexOf("/"));
        group = "group1";
        String path = previewFile.getFileUrl().substring(previewFile.getFileUrl().indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();


        OutputStream outputStream = null;
        try {
            outputStream = httpServletResponse.getOutputStream();


            int bufferSize = 1024 * 100;
            byte[] bytes;
            if (previewFile.getFileSize() < bufferSize) {
                bytes = fastFileStorageClient.downloadFile(group,
                        path,
                        downloadByteArray);
                outputStream.write(bytes);
                log.debug("文件小于缓冲区大小，一次性加载：fileSize:" + previewFile.getFileSize());
            } else {
                int fileOffset = 0;
                int fileSize = (int) previewFile.getFileSize();
                while ((fileOffset + bufferSize) < previewFile.getFileSize()) {
                    bytes = fastFileStorageClient.downloadFile(group,
                            path,
                            fileOffset,
                            bufferSize,
                            downloadByteArray);
                    outputStream.write(bytes);
                    fileOffset += bufferSize;
                    int percent = (int)((double) fileOffset / (double) fileSize * 100);
                    log.debug("正在下载文件:{}, 进度：{}", previewFile.getFileUrl(), percent + "%");
                }
                bytes = fastFileStorageClient.downloadFile(group,
                        path,
                        fileOffset,
                        previewFile.getFileSize() -  fileOffset,
                        downloadByteArray);
                outputStream.write(bytes);
                log.debug("正在下载文件:{}, 进度：{}", previewFile.getFileUrl(), 100 + "%");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public InputStream getInputStream(String fileUrl) {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        group = "group1";
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return inputStream;
    }
}
