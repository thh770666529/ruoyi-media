package com.ruoyi.common.utils.ffmpeg;

public abstract class FFMPEGLocator {


    protected abstract String getFFMPEGExecutablePath();

    FFMPEGExecutor createExecutor() {
        return new FFMPEGExecutor(getFFMPEGExecutablePath());
    }

}
