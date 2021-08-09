package com.ruoyi.common.utils.ffmpeg;

import com.ruoyi.common.constant.FfmpegConstant;
import com.ruoyi.common.enums.FfmpegResolutionEnum;
import com.ruoyi.common.utils.ffmpeg.m3u8.M3u8VideoUtils;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 *
 */
public class VideoUtils {

    private static final Logger log = LoggerFactory.getLogger(VideoUtils.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //System.out.println(videoScreenshot("http://c7c6ea5c95c6a.cdn.sohucs.com/shortvideo/20d8bbda-0dff-4475-8217-e60d55e04650.mp4", "2.00"));
        String inputStr = "D:\\ruoyi\\uploadPath\\movie\\video\\2021\\07\\10\\acebd8a0-9b20-4a72-9c9d-e41c831b77a1\\acebd8a0-9b20-4a72-9c9d-e41c831b77a1.mp4";
        String outStr = "C:\\Users\\King\\Desktop\\ruoyi\\";
        //String outStr = "C:\\Users\\King\\Desktop\\ruoyi\\hls\\";
        boolean b1 = convertMp4(inputStr, outStr, FfmpegResolutionEnum.RESOLUTION_1080P);
        //boolean b1 = generateM3u8(inputStr, outStr);
       // String sourcePath = "C:\\Users\\King\\Desktop\\ruoyi\\5a9eba75-b89d-4772-9208-375c6cb905be\\5a9eba75-b89d-4772-9208-375c6cb905be.mp4";
        //generateAllM3u8(sourcePath);
        long end = System.currentTimeMillis();
        log.info("耗时={}", (end - start));
    }


    public static void generateAllM3u8(String sourceFilePath) {
        File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            log.error("文件不存在！sourceFilePath{}", sourceFilePath);
        }

        MultimediaInfo multimediaInfo = getVideoInfoByFile(sourceFilePath);
        VideoInfo video = multimediaInfo.getVideo();
        VideoSize size = video.getSize();
        int width = size.getWidth();
        //源文件可能不是mp4格式 首先需要转成mp4格式
        String rootFolder = sourceFile.getParent();
        String sourceFileName = sourceFile.getName();
        String extension = FilenameUtils.getExtension(sourceFilePath);
        String folder1080p = rootFolder + File.separator + "1080";
        String folder720p = rootFolder + File.separator + "720";
        String folder360p = rootFolder + File.separator + "360";
        if (width >= 1920) {
            boolean convert1080pFlag = convertMp4(sourceFilePath, folder1080p, FfmpegResolutionEnum.RESOLUTION_1080P);
            generateM3u8(folder1080p + File.separator + sourceFileName, folder1080p + File.separator + "hls");
            boolean convert720pFlag = convertMp4(sourceFilePath, folder720p, FfmpegResolutionEnum.RESOLUTION_720P);
            generateM3u8(folder720p + File.separator + sourceFileName, folder720p + File.separator + "hls");
            boolean convert360pFlag = convertMp4(sourceFilePath, folder360p, FfmpegResolutionEnum.RESOLUTION_360P);
            generateM3u8(folder360p + File.separator + sourceFileName, folder360p + File.separator + "hls");
        } else if (width < 1920 && width >= 720) {
            boolean convert720pFlag = convertMp4(sourceFilePath, folder720p, FfmpegResolutionEnum.RESOLUTION_720P);
            generateM3u8(folder720p + File.separator + sourceFileName, folder720p + File.separator + "hls");
            boolean convert360pFlag = convertMp4(sourceFilePath, folder360p, FfmpegResolutionEnum.RESOLUTION_360P);
            generateM3u8(folder360p + File.separator + sourceFileName, folder360p + File.separator + "hls");

        } else {
            boolean convert360pFlag = convertMp4(sourceFilePath, folder360p, FfmpegResolutionEnum.RESOLUTION_360P);
            generateM3u8(folder360p + File.separator + sourceFileName, folder360p + File.separator + "hls");
        }
    }


    /**
     * @param source
     * @param target
     * @return
     * @desc 检查视频时间是否一致
     */
    public Boolean checkVideoTime(String source, String target) {
        String sourceTime = getVideoInfoByFile(source).getVideoTime();
        String targetTime = getVideoInfoByFile(target).getVideoTime();
        if (sourceTime == null || targetTime == null) {
            return false;
        }
        if (sourceTime.equals(targetTime)) {
            return true;
        }
        return false;
    }

    /**
     * generateM3u8
     *
     * @param videoPath
     * @param outFolderPath
     * @return
     */
    public static boolean generateM3u8(String videoPath, String outFolderPath) {

        try {
            M3u8VideoUtils.clearM3u8(outFolderPath);
            Encoder encoder = new Encoder();
            return encoder.generateM3u8(videoPath, outFolderPath);
        } catch (Exception e) {
            log.error("转化eM3u8视频失败 videoPath=" + videoPath, e);
        }
        return false;
    }

    /**
     * 转化mp4视频
     *
     * @param videoPath
     * @param outFolderPath
     * @return
     */
    public static boolean convertMp4(String videoPath, String outFolderPath, FfmpegResolutionEnum ffmpegResolutionEnum) {
        File outFolder = new File(outFolderPath);
        if (!outFolder.exists()) {
            outFolder.mkdirs();
        }
        String output = outFolder.getPath() + File.separator + FilenameUtils.getName(videoPath);
        File target = new File(output);
        if (target.exists() && target.isFile()) {
            return false;
        }
        try {
            Encoder encoder = new Encoder();
            return encoder.convertMp4(videoPath, output, ffmpegResolutionEnum);
        } catch (Exception e) {
            log.error("转化mp4视频失败 videoPath=" + videoPath, e);
        }
        return false;
    }

    /**
     * 转化mp4视频
     *
     * @param videoPath
     * @param outFolderPath
     * @return
     */
    public static boolean convertMp4(String videoPath, String outFolderPath) {
        return convertMp4(videoPath, outFolderPath, FfmpegResolutionEnum.RESOLUTION_1080P);
    }

    /**
     * 通过视频文件获取视频信息
     *
     * @param videoPath
     * @return
     */
    public static MultimediaInfo getVideoInfoByFile(String videoPath) {
        MultimediaInfo m = new MultimediaInfo();
        try {
            File file = new File(videoPath);
            Encoder encoder = new Encoder();
            m = encoder.getInfoByFile(file);
            if (null != m) {
                m.setVideoSize(file.length());
            }
            return m;
        } catch (Exception e) {
            log.error("获取播放播放时长异常 videoPath=" + videoPath, e);
        }
        return m ;
    }

    /**
     * 通过视频地址获取视频信息
     *
     * @param videoUrl
     * @return
     */
    public static MultimediaInfo getVideoInfoByUrl(String videoUrl, String ua, int timeout, boolean ifProxy) {
        try {
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfoByUrl(videoUrl, ua, timeout, ifProxy);
            long end = System.currentTimeMillis();
            log.info("获取视频宽高时长,duration={}; 耗时={}", m.getDuration(), (end - start));
            return m;
        } catch (Exception e) {
            log.error("获取视频信息异常 videoUrl=" + videoUrl, e);
        }
        return null;
    }

    /**
     * 下载m3u8视频
     *
     * @param url    m3u8播放地址
     * @param output 视频输出路径
     * @return
     */
    public static boolean downloadAndMergeM3U8Video(String url, String output) {
        try {
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            boolean b = encoder.mergeM3U8Video(url, output);
            long end = System.currentTimeMillis();
            log.info("url={} output={} m3u8视频耗时={}", url, output, (end - start));
            return b;
        } catch (Exception e) {
            log.error("合并视频异常 url={} output={}", url, output, e);
        }
        return false;
    }

    /**
     * 合并视频
     *
     * @param output 视频的输出位置
     * @param input  分段视频
     * @return
     */
    public static boolean mergeVideo(String output, List<String> input) {
        try {
            if (null == output || null == input) {
                return false;
            }
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            boolean b = encoder.mergeVideo(output, input.toArray(new String[input.size()]));
            long end = System.currentTimeMillis();
            log.info("input={} output={} 合并视频耗时={}", input, output, (end - start));
            return b;
        } catch (Exception e) {
            log.error("合并视频异常 input=" + input + " output" + output, e);
        }
        return false;
    }

    /**
     * 截封面图
     *
     * @param input     视频文件或地址
     * @param time      截图的固定时间点
     * @param imgOutPut 图片的输出路径
     * @return 是否成功
     */
    public static boolean videoScreenshot(String input, String time, String imgOutPut) {
        try {
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            String imgPath = null;
            if (null == imgOutPut) {
                File temp = new File(System.getProperty("user.dir"), "work");
                if (!temp.exists()) {
                    temp.mkdirs();
                }
                imgPath = temp.getAbsolutePath() + File.separator + UUID.randomUUID().toString() + ".png";
            } else {
                imgPath = imgOutPut;
            }
            boolean b = encoder.videoScreenshot(input, time, imgPath);
            long end = System.currentTimeMillis();
            log.info("input={} imgPath={} 截图耗时={}", input, imgOutPut, (end - start));
            return b;
        } catch (Exception e) {
            log.error("视频截图异常 time=" + time + " output" + input, e);
        }
        return false;
    }

    /**
     * 压缩视频
     *
     * @param output
     * @param input
     * @return
     */
    public static boolean compressVideo(String output, String input) {
        try {
            long start = System.currentTimeMillis();
            Encoder encoder = new Encoder();
            boolean b = encoder.compressVideo(output, input);
            long end = System.currentTimeMillis();
            log.info("input=" + input + " output=" + output + "压缩视频耗时=" + (end - start));
            return b;
        } catch (Exception e) {
            log.error("压缩视频异常 input=" + input + " output" + output, e);
        }
        return false;
    }


}
