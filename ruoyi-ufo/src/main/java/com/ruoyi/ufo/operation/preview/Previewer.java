package com.ruoyi.ufo.operation.preview;


import com.ruoyi.ufo.operation.preview.domain.PreviewFile;
import javax.servlet.http.HttpServletResponse;

public abstract class Previewer {

    public abstract void imageThumbnailPreview(HttpServletResponse httpServletResponse, PreviewFile previewFile);

    public abstract void imageOriginalPreview(HttpServletResponse httpServletResponse, PreviewFile previewFile);
}
