
package com.ruoyi.common.utils.ffmpeg;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Instances of this class report informations about videos size.
 *
 * @author Carlo Pelliccia
 */
@Data
public class VideoSize implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The video width.
	 */
	private int width;


	private int height;

	public VideoSize() {
	}

	/**
	 * It builds the bean.
	 *
	 * @param width
	 *            The video width.
	 * @param height
	 *            The video height.
	 */
	public VideoSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

}
