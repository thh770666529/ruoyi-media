package com.ruoyi.ufo.operation.download.product;

import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ruoyi.ufo.operation.download.Downloader;
import com.ruoyi.ufo.operation.download.domain.DownloadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
@Component
public class FastDFSDownloader extends Downloader {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Override
    public void download(HttpServletResponse httpServletResponse, DownloadFile downloadFile) {
        String group = downloadFile.getFileUrl().substring(0, downloadFile.getFileUrl().indexOf("/"));
        group = "group1";
        String path = downloadFile.getFileUrl().substring(downloadFile.getFileUrl().indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();


        OutputStream outputStream = null;
        try {
            outputStream = httpServletResponse.getOutputStream();


            int bufferSize = 1024 * 100;
            byte[] bytes;
            if (downloadFile.getFileSize() < bufferSize) {
                bytes = fastFileStorageClient.downloadFile(group,
                        path,
                        downloadByteArray);
                outputStream.write(bytes);
                log.debug("文件小于缓冲区大小，一次性加载：fileSize:" + downloadFile.getFileSize());
            } else {
                int fileOffset = 0;
                int fileSize = (int) downloadFile.getFileSize();
                while ((fileOffset + bufferSize) < downloadFile.getFileSize()) {
                    bytes = fastFileStorageClient.downloadFile(group,
                            path,
                            fileOffset,
                            bufferSize,
                            downloadByteArray);
                    outputStream.write(bytes);
                    fileOffset += bufferSize;
                    int percent = (int)((double) fileOffset / (double) fileSize * 100);
                    log.debug("正在下载文件:{}, 进度：{}", downloadFile.getFileUrl(), percent + "%");
                }
                bytes = fastFileStorageClient.downloadFile(group,
                        path,
                        fileOffset,
                        downloadFile.getFileSize() -  fileOffset,
                        downloadByteArray);
                outputStream.write(bytes);
                log.debug("正在下载文件:{}, 进度：{}", downloadFile.getFileUrl(), 100 + "%");
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

    @Override
    public InputStream getInputStream(DownloadFile downloadFile) {
        String group = downloadFile.getFileUrl().substring(0, downloadFile.getFileUrl().indexOf("/"));
        group = "group1";
        String path = downloadFile.getFileUrl().substring(downloadFile.getFileUrl().indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        return inputStream;
    }
}
