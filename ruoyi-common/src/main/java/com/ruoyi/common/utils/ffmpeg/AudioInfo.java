
package com.ruoyi.common.utils.ffmpeg;

import lombok.Data;

/**
 * Instances of this class report informations about an audio stream that can be
 * decoded.
 *
 * @author thh
 */
@Data
public class AudioInfo {

    /**
     * The audio stream decoder name.
     */
    private String decoder;

    /**
     * The audio stream sampling rate. If less than 0, this information is not
     * available.
     */
    private int samplingRate = -1;

    /**
     * The audio stream channels number (1=mono, 2=stereo). If less than 0, this
     * information is not available.
     */
    private int channels = -1;

    /**
     * The audio stream (average) bit rate. If less than 0, this information is
     * not available.
     */
    private int bitRate = -1;

    @Override
    public String toString() {
        return getClass().getName() + " (decoder=" + decoder + ", samplingRate="
                + samplingRate + ", channels=" + channels + ", bitRate="
                + bitRate + ")";
    }

}
