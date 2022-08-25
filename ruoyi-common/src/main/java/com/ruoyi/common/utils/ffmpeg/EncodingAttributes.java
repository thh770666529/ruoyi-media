
package com.ruoyi.common.utils.ffmpeg;

import lombok.Data;

import java.io.Serializable;

/**
 * Attributes controlling the encoding process.
 *
 * @author thh
 */
@Data
public class EncodingAttributes implements Serializable {

    private static final long serialVersionUID = 1L;


    private String format = null;

    /**
     * The start offset time (seconds). If null or not specified no start offset
     * will be applied.
     */
    private Float offset = null;

    /**
     * The duration (seconds) of the re-encoded stream. If null or not specified
     * the source stream, starting from the offset, will be completely
     * re-encoded in the target stream.
     */
    private Float duration = null;

    /**
     * The attributes for the encoding of the audio stream in the target
     * multimedia file. If null of not specified no audio stream will be
     * encoded. It cannot be null if also the video field is null.
     */
    private AudioAttributes audioAttributes = null;

    /**
     * The attributes for the encoding of the video stream in the target
     * multimedia file. If null of not specified no video stream will be
     * encoded. It cannot be null if also the audio field is null.
     */
    private VideoAttributes videoAttributes = null;

    @Override
    public String toString() {
        return getClass().getName() + "(format=" + format + ", offset="
                + offset + ", duration=" + duration + ", audioAttributes="
                + audioAttributes + ", videoAttributes=" + videoAttributes
                + ")";
    }

}
