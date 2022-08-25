package com.ruoyi.media.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.*;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.config.JustAuthConfig;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.MovieActorType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.index.vo.SearchParamVO;
import com.ruoyi.media.domain.MovieActor;
import com.ruoyi.media.domain.vo.MovieActorVO;
import com.ruoyi.media.domain.vo.MovieVO;
import com.ruoyi.media.mapper.MovieActorMapper;
import com.ruoyi.media.mapper.VideoMapper;
import com.ruoyi.media.service.IVideoService;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.util.TokenUtil;
import com.ruoyi.website.domain.WebConfig;
import com.ruoyi.website.service.IWebConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.mapper.MovieMapper;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.service.IMovieService;

/**
 * 电影Service业务层处理
 *
 * @author thh
 * @date 2021-05-16
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {
    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private IVideoService videoService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private MovieActorMapper movieActorMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IWebConfigService webConfigService;

    @Autowired
    JustAuthConfig justAuthConfig;

    /**
     * 查询电影
     *
     * @param movieId 电影ID
     * @return 电影
     */
    @Override
    public MovieVO selectMovieById(Long movieId) {
        Movie movie = movieMapper.selectById(movieId);
        MovieVO movieVO = new MovieVO();
        if (movie != null) {
            BeanUtils.copyProperties(movie, movieVO);
            String publishBy = movieVO.getPublishBy();
            if (StringUtils.isNotEmpty(publishBy)) {
                SysUser sysUser = sysUserMapper.selectUserById(Long.valueOf(publishBy));
                movieVO.setPublishUsername(sysUser != null ? sysUser.getNickName() : null);
            }
            movieVO.setVideoList(videoService.selectVideoByMovieId(movieId));

            //查询演员和导演信息
            MovieActorVO movieActorQuery = new MovieActorVO();
            movieActorQuery.setMovieId(movieVO.getMovieId());
            movieActorQuery.setType(MovieActorType.ACTOR.getValue());
            List<MovieActorVO> actorList = movieActorMapper.selectMovieActorList(movieActorQuery);
            movieActorQuery.setType(MovieActorType.DIRECTOR.getValue());
            List<MovieActorVO> directorList = movieActorMapper.selectMovieActorList(movieActorQuery);
            movieVO.setDirectorList(directorList);
            movieVO.setActorList(actorList);

        }
        return movieVO;
    }

    @Override
    public MovieVO selectWebMovieById(Long movieId) {
        MovieVO movieVO = movieMapper.selectWebMovieById(movieId);
        movieVO.setVideoList(videoService.selectVideoByMovieId(movieId));
        return movieVO;
    }

    /**
     * 查询电影列表
     *
     * @param movie 电影
     * @return 电影
     */
    @Override
    public List<MovieVO> selectMovieList(Movie movie) {
        return movieMapper.selectMovieList(movie);
    }

    /**
     * 新增电影
     *
     * @param movieVO 电影
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMovie(MovieVO movieVO) {
        // 初始化数据
        this.initData(movieVO);
        int rows = movieMapper.insert(movieVO);
        if (rows > 0) {
            this.insertVideo(movieVO);
            this.insertActor(movieVO, MovieActorType.ACTOR);
            this.insertActor(movieVO, MovieActorType.DIRECTOR);
            String url = "movie/" + movieVO.getMovieId();
            String qrcodePath = generateQr(url);
            movieVO.setQrcodePath(qrcodePath);
            movieMapper.updateById(movieVO);
        }
        return rows;
    }

    /**
     * 初始化数据
     *
     * @param movieVO
     */
    private void initData(MovieVO movieVO) {
        String title = movieVO.getTitle();
        if (StringUtils.isNotBlank(title)) {
            String en = PinyinUtil.getPinyin(title, "");
            String letter = PinyinUtil.getFirstLetter(title, "");
            movieVO.setEn(en);
            movieVO.setLetter(letter.toUpperCase());
        }
        // 设置视频总长度
        List<Video> videoList = movieVO.getVideoList();
        if (videoList == null || videoList.size() == 0) {
            return;
        }
        long totalVideoLength = 0;
        for (Video video : videoList) {
            String length = video.getLength();
            if (StringUtils.isNotEmpty(length)) {
                totalVideoLength += Long.valueOf(length);
            }
        }
        movieVO.setTotalVideoLength(totalVideoLength);
    }

    /**
     * @param movieVO
     * @Desc 更新演员
     */
    private void insertActor(MovieVO movieVO, MovieActorType movieActorType) {
        Long movieId = movieVO.getMovieId();
        List<MovieActorVO> actorList = movieVO.getActorList();
        List<MovieActorVO> directorList = movieVO.getDirectorList();

        List<MovieActorVO> insertList = null;
        switch (movieActorType) {
            case ACTOR:
                insertList = actorList;
                break;
            case DIRECTOR:
                insertList = directorList;
                break;
            default:
                return;
        }
        if (StringUtils.isNotNull(insertList)) {
            for (MovieActorVO movieActorVO : insertList) {
                MovieActor movieActor = new MovieActor();
                BeanUtils.copyProperties(movieActorVO, movieActor);
                movieActor.setMovieId(movieId);
                movieActor.setType(movieActorType.getValue());
                movieActorMapper.insertMovieActor(movieActor);
            }
        }
    }

    /**
     * 修改电影
     *
     * @param movieVO 电影
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMovie(MovieVO movieVO) {
        videoMapper.deleteByMovieId(movieVO.getMovieId());
        // 初始化数据
        this.initData(movieVO);
        this.insertVideo(movieVO);
        Map movieActorMap = new HashMap<>();
        movieActorMap.put("movie_id", movieVO.getMovieId());
        movieActorMapper.deleteByMap(movieActorMap);
        this.insertActor(movieVO, MovieActorType.ACTOR);
        this.insertActor(movieVO, MovieActorType.DIRECTOR);
        if (StringUtils.isBlank(movieVO.getQrcodePath())) {
            String url = "movie/" + movieVO.getMovieId();
            String qrcodePath = generateQr(url);
            movieVO.setQrcodePath(qrcodePath);
        }
        return movieMapper.updateById(movieVO);
    }


    /**
     * 删除电影信息
     *
     * @param movieId 电影ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteMovieById(Long movieId) {
        videoMapper.deleteByMovieId(movieId);
        return movieMapper.deleteById(movieId);
    }

    @Override
    public List<MovieVO> selectHotMovieList(int top) {
        return movieMapper.selectHotMovieList(top);
    }

    @Override
    public int removeActorList(Long[] actorIds) {
        return movieActorMapper.deleteMovieActorByIds(actorIds);
    }

    @Override
    public List<MovieVO> getListByActorId(Long actorId) {
        return movieMapper.selectListByActorId(actorId);
    }

    @Override
    public List<Movie> getSameTypeMovieList(Long movieId) {
        MovieVO movieVO = this.selectMovieById(movieId);
        Integer categoryId = movieVO.getCategoryId();
        Page<Movie> page = new Page<>(1, 12);
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.notIn("movie_id", movieId);
        queryWrapper.select("movie_id", "images", "title", "type", "country", "tag_id", "publish_by", "publish_time", "open_comment", "rate", "category_id", "category_name");
        queryWrapper.orderByDesc("create_time");
        return movieMapper.selectPage(page, queryWrapper).getRecords();
    }

    /**
     * 新增电影视频信息
     *
     * @param movieVO 电影对象
     */
    public void insertVideo(MovieVO movieVO) {
        List<Video> videoList = movieVO.getVideoList();
        Long movieId = movieVO.getMovieId();
        if (StringUtils.isNotNull(videoList)) {
            List<Video> list = new ArrayList<Video>();
            for (Video video : videoList) {
                video.setMovieId(movieId);
                list.add(video);
            }
            if (list.size() > 0) {
                videoMapper.batchVideo(list);
            }
        }
    }

    @Override
    public List<MovieVO> selectWebMovieList(MovieVO movieVO) {
        return movieMapper.selectWebMovieList(movieVO);
    }

    @Override
    public List<MovieVO> search(SearchParamVO searchParamVO) {
        List<MovieVO> movieVOList = movieMapper.searchWebMovieList(searchParamVO);
        String keyword = searchParamVO.getKeyword();
        movieVOList.forEach(item -> {
            // 给标题、简介、分类 设置高亮
            item.setTitle(getHitCode(item.getTitle(), keyword));
            item.setDescription(getHitCode(item.getDescription(), keyword));
            item.setCategoryName(getHitCode(item.getCategoryName(), keyword));
        });
        return movieVOList;
    }


    /**
     * 添加高亮
     *
     * @param str     截取字符串
     * @param keyword 关键字
     * @return 返回添加了高亮的字符串
     */
    private String getHitCode(String str, String keyword) {
        if (StringUtils.isAnyBlank(str, keyword)) {
            return "";
        }
        String startStr = "<span style = 'color:red'>";
        String endStr = "</span>";
        // 判断关键字是否直接是搜索的内容，否者直接返回
        if (str.equalsIgnoreCase(keyword)) {
            return startStr + str + endStr;
        }
        String lowerCaseStr = str.toLowerCase();
        String lowerKeyword = keyword.toLowerCase();
        String[] lowerCaseArray = lowerCaseStr.split(lowerKeyword);
        boolean isEndWith = lowerCaseStr.endsWith(lowerKeyword);

        // 计算分割后的字符串位置
        int count = 0;
        List<Map<String, Integer>> list = new ArrayList<>();
        List<Map<String, Integer>> keyList = new ArrayList<>();
        for (int index = 0; index < lowerCaseArray.length; index++) {
            // 将切割出来的存储map
            Map<String, Integer> map = new HashMap<>();
            Map<String, Integer> keyMap = new HashMap<>();
            map.put("startIndex", count);
            int len = lowerCaseArray[index].length();
            count += len;
            map.put("endIndex", count);
            list.add(map);
            if (index < lowerCaseArray.length - 1 || isEndWith) {
                // 将keyword存储map
                keyMap.put("startIndex", count);
                count += keyword.length();
                keyMap.put("endIndex", count);
                keyList.add(keyMap);
            }
        }
        // 截取切割对象
        List<String> arrayList = new ArrayList<>();
        for (Map<String, Integer> item : list) {
            Integer start = item.get("startIndex");
            Integer end = item.get("endIndex");
            String itemStr = str.substring(start, end);
            arrayList.add(itemStr);
        }
        // 截取关键字
        List<String> keyArrayList = new ArrayList<>();
        for (Map<String, Integer> item : keyList) {
            Integer start = item.get("startIndex");
            Integer end = item.get("endIndex");
            String itemStr = str.substring(start, end);
            keyArrayList.add(itemStr);
        }

        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < arrayList.size(); index++) {
            sb.append(arrayList.get(index));
            if (index < arrayList.size() - 1 || isEndWith) {
                sb.append(startStr);
                sb.append(keyArrayList.get(index));
                sb.append(endStr);
            }
        }
        return sb.toString();
    }

    /**
     * 生成二维码
     *
     * @param url
     * @return
     */
    private String generateQr(String url) {
        String profile = RuoYiConfig.getProfile();
        WebConfig webConfig = webConfigService.getWebConfig();
        String logoURL = webConfig.getLogo();
        String logoPath = profile + logoURL;
        QrConfig qrConfig = QrConfig.create()
                .setWidth(300)
                .setHeight(300);
        File logoFile = new File(logoPath);
        if (logoFile.exists()) {
            qrConfig.setImg(logoFile);
        }
        String qrRootPath = RuoYiConfig.getQrPath();
        String websiteUrl = justAuthConfig.getWebsiteUrl();
        String fileName = DateUtils.datePath() + "/" + IdUtils.fastUUID().replace("-", "") + ".jpg";
        String realQrPath = qrRootPath + File.separator + fileName;
        File realQrFile = new File(realQrPath);
        if (!realQrFile.exists()) {
            if (!realQrFile.getParentFile().exists()) {
                realQrFile.getParentFile().mkdirs();
            }
        }
        File file = QrCodeUtil.generate(websiteUrl + url, qrConfig, FileUtil.file(realQrFile));
        int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(qrRootPath, dirLastIndex);
        if (file.exists()) {
            return "/" + currentDir + "/" + fileName;
        } else {
            return "";
        }
    }
}
