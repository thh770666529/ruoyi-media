package com.ruoyi.common.enums;

/**
 * @ClassName FfmpegEnum
 * @Description 分辨率类型
 * @Author thh
 * @Date 12:11 2021/07/10
 **/
public enum  FfmpegResolutionEnum {

    RESOLUTION_1080P("1920x1080",1920,1080,2600,2600,25,128,2,48),

    RESOLUTION_720P("1280x720",1280,720,1800,1800,25,64,2,48),

    RESOLUTION_360P("720x480",720,480,1500,1500,18,64,2,48);

    private final String code;

    //帧宽度
    private final int width;

    //帧高度
    private final int height;

    //数据速率（kbps）
    private final int dataRate;

    //总比特率（kbps）
    private final int totalBitRate;

    //帧速率 （帧/秒）
    private final int frameRate;

    // 比特率 （kbps）
    private final int bitRate;

    // 频道（立体声）
    private final int chanel;

    // 音频采样频率
    private final int audioSampingFrequency;

    FfmpegResolutionEnum(String code, int width, int height, int dataRate, int totalBitRate, int frameRate, int bitRate, int chanel, int audioSampingFrequency) {
        this.code = code;
        this.width = width;
        this.height = height;
        this.dataRate = dataRate;
        this.totalBitRate = totalBitRate;
        this.frameRate = frameRate;
        this.bitRate = bitRate;
        this.chanel = chanel;
        this.audioSampingFrequency = audioSampingFrequency;
    }

    public String getCode() {
        return code;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDataRate() {
        return dataRate;
    }

    public int getTotalBitRate() {
        return totalBitRate;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public int getBitRate() {
        return bitRate;
    }

    public int getChanel() {
        return chanel;
    }

    public int getAudioSampingFrequency() {
        return audioSampingFrequency;
    }
}
