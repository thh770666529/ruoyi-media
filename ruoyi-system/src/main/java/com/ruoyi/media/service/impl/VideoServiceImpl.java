package com.ruoyi.media.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.VideoStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.operation.FileOperation;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ffmpeg.MultimediaInfo;
import com.ruoyi.common.utils.ffmpeg.VideoUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.file.domain.FileBean;
import com.ruoyi.file.service.IFileService;
import com.ruoyi.media.vo.NetWorkDiskVO;
import com.ruoyi.media.vo.UploadVideoVO;
import com.ruoyi.ufo.util.PathUtil;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.media.mapper.VideoMapper;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.service.IVideoService;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;

/**
 * 电影视频Service业务层处理
 *
 * @author thh
 * @date 2021-05-16
 */
@Service
public class VideoServiceImpl  extends ServiceImpl<VideoMapper, Video> implements IVideoService
{
    private static final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoMapper videoMapper;

    @Resource
    IFileService fileService;

    @Override
    public List<Video> selectVideoList(Video video) {
        return videoMapper.selectVideoList(video);
    }

    @Override
    public UploadVideoVO uploadVideoByNetWorkDisk(NetWorkDiskVO netWorkDiskVO) {
        FileBean fileBean = fileService.getById(netWorkDiskVO.getFileId());
        //拿到网盘的本地真实路径
        File file = FileOperation.newFile(PathUtil.getStaticPath() + fileBean.getFileUrl());
        MultimediaInfo videoInfoByFile = VideoUtils.getVideoInfoByFile(file.getPath());
        //复制到视频目录
        String fileName = FileUploadUtils.extractFilename2(file);
        File newVideoFile = new File(RuoYiConfig.getMovieVideoPath()+"" + File.separator + fileName);
        if (!newVideoFile.exists()) {
            if (!newVideoFile.getParentFile().exists()) {
                newVideoFile.getParentFile().mkdirs();
            }
        }
        try {
            FileCopyUtils.copy(file,newVideoFile);
        }catch (IOException e){
            log.error(" 上传视频失败,请联系管理员{}",e.getMessage(), e);
            throw new ServiceException("上传视频失败,请联系管理员！");
        }
        int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(RuoYiConfig.getMovieVideoPath(), dirLastIndex);
        String pathFileName = "/" + currentDir + "/" + fileName;
        String status = VideoStatus.READY_CONVERT.getCode();
        UploadVideoVO uploadVideoVO = new UploadVideoVO(pathFileName, netWorkDiskVO.getFileName() , FilenameUtils.getExtension(pathFileName), fileBean.getFileSize(), videoInfoByFile.getVideoTime(),status);
        return uploadVideoVO;
    }
}
