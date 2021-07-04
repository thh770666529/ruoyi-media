package com.ruoyi.common.utils.ffmpeg;

import lombok.Data;

import java.util.Formatter;
import java.util.Locale;

/**
 * Instances of this class report informations about a decoded multimedia file.
 *
 * @author Carlo Pelliccia
 */
@Data
public class MultimediaInfo {

    /**
     * The multimedia file format name.
     */
    private String format = null;

    private long videoSize = 0;

    /**
     * The stream duration in millis. If less than 0 this information is not
     * available.
     */
    private long duration = -1;

    /**
     * A set of audio-specific informations. If null, there's no audio stream in
     * the multimedia file.
     */
    private AudioInfo audio = null;

    /**
     * A set of video-specific informations. If null, there's no video stream in
     * the multimedia file.
     */
    private VideoInfo video = null;


    public String getVideoTime() {
        return stringForTime(duration);
    }

    private String stringForTime(long timeMs) {
        long totalSeconds = timeMs / 1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        mFormatBuilder.setLength(0);
        if (hours > 0) {
            //超过60分钟的，显示出小时
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            //不足60分钟的，只显示分和秒
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + " (format=" + format + ", duration="
                + duration + ", video=" + video + ", audio=" + audio + ", videoSize=" + videoSize + ")";
    }

}
