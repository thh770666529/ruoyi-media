
package com.ruoyi.common.utils.ffmpeg;

/**
 * Encoding expection.
 *
 * @author thh
 */
public class EncoderException extends Exception {

    private static final long serialVersionUID = 1L;

    public EncoderException() {
    }

    EncoderException(String message) {
        super(message);
    }

    EncoderException(Throwable cause) {
        super(cause);
    }

    public EncoderException(String message, Throwable cause) {
        super(message, cause);
    }


}
