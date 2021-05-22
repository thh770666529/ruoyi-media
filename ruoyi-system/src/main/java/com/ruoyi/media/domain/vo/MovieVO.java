package com.ruoyi.media.domain.vo;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.domain.MovieActor;
import com.ruoyi.media.domain.Video;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 电影管理 MovieVO
 *
 * @author thh
 * @date 2021-05-01
 */
@Data
public class MovieVO extends Movie
{
    private static final long serialVersionUID = 1L;

    /** 电影视频信息 */
    private List<Video> videoList;

    /** 演员信息 */
    private List<MovieActorVO> actorList;

    /** 演员信息 */
    private List<MovieActorVO> directorList;

    /**
     * 发布人姓名
     */
    private String publishUsername;

}
