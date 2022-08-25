
package com.ruoyi.common.utils.ffmpeg;

/**
 * Encoding progress listener interface. Instances of implementing classes could
 * be used to listen an encoding process.
 *
 * @author thh
 */
public interface EncoderProgressListener {

    /**
     * This method is called before the encoding process starts, reporting
     * information about the source stream that will be decoded and re-encoded.
     *
     * @param info Informations about the source multimedia stream.
     */
    public void sourceInfo(MultimediaInfo info);

    /**
     * This method is called to notify a progress in the encoding process.
     *
     * @param permil A permil value representing the encoding process progress.
     */
    public void progress(int permil);

    /**
     * This method is called every time the encoder need to send a message
     * (usually, a warning).
     *
     * @param message The message sent by the encoder.
     */
    public void message(String message);

}
