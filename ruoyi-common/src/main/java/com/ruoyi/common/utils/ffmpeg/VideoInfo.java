package com.ruoyi.common.utils.ffmpeg;

import lombok.Data;

/**
 * Instances of this class report informations about a video stream that can be
 * decoded.
 *
 * @author thh
 */
@Data
public class VideoInfo {

    /**
     * The video stream decoder name.
     */
    private String decoder;

    /**
     * The video size. If null this information is not available.
     */
    private VideoSize size = null;

    /**
     * The video stream (average) bit rate. If less than 0, this information is
     * not available.
     */
    private int bitRate = -1;

    /**
     * The video frame rate. If less than 0 this information is not available.
     */
    private float frameRate = -1;

    /**
     * Returns the video stream decoder name.
     *
     * @return The video stream decoder name.
     */
    public String getDecoder() {
        return decoder;
    }

    /**
     * Sets the video stream decoder name.
     *
     * @param decoder The video stream decoder name.
     */
    void setDecoder(String decoder) {
        this.decoder = decoder;
    }

}
