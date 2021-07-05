package com.ruoyi.media.task;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.FfmpegConstant;
import com.ruoyi.common.enums.VideoStatus;
import com.ruoyi.common.utils.ffmpeg.MultimediaInfo;
import com.ruoyi.common.utils.ffmpeg.VideoInfo;
import com.ruoyi.common.utils.ffmpeg.VideoSize;
import com.ruoyi.common.utils.ffmpeg.VideoUtils;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.mapper.VideoMapper;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName MediaTask
 * @Description 媒资定时任务
 * @Author thh
 * @Date 20:05 2021/07/05
 **/
@Component("mediaTask")
public class MediaTask {

    private static final Logger log = LoggerFactory.getLogger(MediaTask.class);

    @Resource
    private VideoMapper videoMapper;

    public void convertVideoList(){
        System.out.println("videoMapper = ");
        Video videoCondition = new Video();
        videoCondition.setStatus(VideoStatus.READY_CONVERT.getCode());
        List<Video> videoList = videoMapper.selectVideoList(videoCondition);

        List<Video> readyConvertVideoList = videoList.stream().map(
                item -> {
                    item.setStatus(VideoStatus.CONVERTING.getCode());
                    videoMapper.updateById(item);
                    return item;
                })
                .collect(Collectors.toList());
        for (Video video : readyConvertVideoList) {
            String url = video.getUrl();
            Video newVideo = new Video();
            url = RuoYiConfig.getProfile() + File.separator +url;
            try {
                generateAllM3u8(url,newVideo);
            }catch (Exception e){
                newVideo.setErrorMsg(e.getMessage());
            }
            BeanUtils.copyProperties(video,newVideo);
            newVideo.setStatus(VideoStatus.CONVERT_SUCCESS.getCode());
            videoMapper.updateById(newVideo);
        }

    }


    private void generateAllM3u8(String sourceFilePath,Video newVideo) {
        File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            log.error("文件不存在！sourceFilePath{}", sourceFilePath);
        }
        MultimediaInfo multimediaInfo = VideoUtils.getVideoInfoByFile(sourceFilePath);
        VideoInfo video = multimediaInfo.getVideo();
        VideoSize size = video.getSize();
        int width = size.getWidth();
        newVideo.setLength(multimediaInfo.getVideoTime());
        //源文件可能不是mp4格式 首先需要转成mp4格式
        String rootFolder = sourceFile.getParent();
        String sourceFileName = sourceFile.getName();
        String extension = FilenameUtils.getExtension(sourceFilePath);
        String folder1080p = rootFolder + File.separator + "1080";
        String folder720p = rootFolder + File.separator + "720";
        String folder360p = rootFolder + File.separator + "360";
        String m3u8Url1080 = "";
        String m3u8Url720 = "";
        String m3u8Url360 = "";
        if (width >= 1920) {
            boolean convert1080pFlag = VideoUtils.convertMp4(sourceFilePath, folder1080p, FfmpegConstant.RESOLUTION_1080P);
            VideoUtils.generateM3u8(folder1080p + File.separator + sourceFileName, folder1080p + File.separator + "hls");
            m3u8Url1080 = folder1080p + File.separator + "hls"+File.separator+sourceFileName+".m3u8";
            boolean convert720pFlag = VideoUtils.convertMp4(sourceFilePath, folder720p, FfmpegConstant.RESOLUTION_720P);
            VideoUtils.generateM3u8(folder720p + File.separator + sourceFileName, folder720p + File.separator + "hls");
            m3u8Url720 = folder720p + File.separator + "hls"+File.separator+sourceFileName+".m3u8";
            boolean convert360pFlag = VideoUtils.convertMp4(sourceFilePath, folder360p, FfmpegConstant.RESOLUTION_360P);
            VideoUtils.generateM3u8(folder360p + File.separator + sourceFileName, folder360p + File.separator + "hls");
            m3u8Url360 = folder360p + File.separator + "hls"+File.separator+sourceFileName+".m3u8";
        } else if (width < 1920 && width >= 720) {
            boolean convert720pFlag = VideoUtils.convertMp4(sourceFilePath, folder720p, FfmpegConstant.RESOLUTION_720P);
            VideoUtils.generateM3u8(folder720p + File.separator + sourceFileName, folder720p + File.separator + "hls");
            m3u8Url720 = folder720p + File.separator + "hls"+File.separator+sourceFileName+".m3u8";
            boolean convert360pFlag = VideoUtils.convertMp4(sourceFilePath, folder360p, FfmpegConstant.RESOLUTION_360P);
            VideoUtils.generateM3u8(folder360p + File.separator + sourceFileName, folder360p + File.separator + "hls");
            m3u8Url360 = folder360p + File.separator + "hls"+File.separator+sourceFileName+".m3u8";
        } else {
            boolean convert360pFlag = VideoUtils.convertMp4(sourceFilePath, folder360p, FfmpegConstant.RESOLUTION_360P);
            VideoUtils.generateM3u8(folder360p + File.separator + sourceFileName, folder360p + File.separator + "hls");
            m3u8Url360 = folder360p + File.separator + "hls"+File.separator+sourceFileName+".m3u8";
        }
    }
}
