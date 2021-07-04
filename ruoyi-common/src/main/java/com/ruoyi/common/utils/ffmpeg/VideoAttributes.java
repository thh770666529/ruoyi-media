package com.ruoyi.common.utils.ffmpeg;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoAttributes implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * This value can be setted in the codec field to perform a direct stream
	 * copy, without re-encoding of the audio stream.
	 */
	public static final String DIRECT_STREAM_COPY = "copy";

	/**
	 * The codec name for the encoding process. If null or not specified the
	 * encoder will perform a direct stream copy.
	 */
	private String codec = null;

	/**
	 * The the forced tag/fourcc value for the video stream.
	 */
	private String tag = null;

	/**
	 * The bitrate value for the encoding process. If null or not specified a
	 * default value will be picked.
	 */
	private Integer bitRate = null;

	/**
	 * The frame rate value for the encoding process. If null or not specified a
	 * default value will be picked.
	 */
	private Integer frameRate = null;

	/**
	 * The video size for the encoding process. If null or not specified the
	 * source video size will not be modified.
	 */
	private VideoSize size = null;


	@Override
	public String toString() {
		return getClass().getName() + "(codec=" + codec + ", bitRate="
				+ bitRate + ", frameRate=" + frameRate + ", size=" + size + ")";
	}

}
