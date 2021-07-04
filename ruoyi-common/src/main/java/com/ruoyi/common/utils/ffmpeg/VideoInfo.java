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

/**
 * Instances of this class report informations about a video stream that can be
 * decoded.
 *
 * @author Carlo Pelliccia
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
	 * @param decoder
	 *            The video stream decoder name.
	 */
	void setDecoder(String decoder) {
		this.decoder = decoder;
	}

}
