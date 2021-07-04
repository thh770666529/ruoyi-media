/*
 * JAVE - A Java Audio/Video Encoder (based on FFMPEG)
 *
 * Copyright (C) 2008-2009 Carlo Pelliccia (www.sauronsoftware.it)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ruoyi.common.utils.ffmpeg;

import lombok.Data;

import java.io.Serializable;

/**
 * Attributes controlling the encoding process.
 *
 * @author Carlo Pelliccia
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
