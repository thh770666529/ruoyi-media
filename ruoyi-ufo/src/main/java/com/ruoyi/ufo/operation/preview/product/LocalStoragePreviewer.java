package com.ruoyi.ufo.operation.preview.product;

import com.ruoyi.common.operation.FileOperation;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.ufo.operation.preview.Previewer;
import com.ruoyi.ufo.operation.preview.domain.PreviewFile;
import com.ruoyi.ufo.util.PathUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class LocalStoragePreviewer extends Previewer {
    @Override
    public void imageThumbnailPreview(HttpServletResponse httpServletResponse, PreviewFile previewFile) {
        String extendName = FileUtil.getFileExtendName(previewFile.getFileUrl());
        previewFile.setFileUrl(previewFile.getFileUrl().replace("." + extendName, "_min." + extendName));
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];
        //设置文件路径
        File file = FileOperation.newFile(PathUtil.getStaticPath() + previewFile.getFileUrl());
        if (file.exists()) {

            FileInputStream fis = null;

            try {
                fis = new FileInputStream(file);
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
        }
    }

    @Override
    public void imageOriginalPreview(HttpServletResponse httpServletResponse, PreviewFile previewFile) {

        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];
        //设置文件路径
        File file = FileOperation.newFile(PathUtil.getStaticPath() + previewFile.getFileUrl());
        if (file.exists()) {

            FileInputStream fis = null;

            try {
                fis = new FileInputStream(file);
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
        }
    }
}
