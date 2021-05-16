package com.ruoyi.web.controller.media;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.media.domain.vo.MovieVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.service.IMovieService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 电影Controller
 *
 * @author thh
 * @date 2021-05-16
 */
@RestController
@RequestMapping("/media/movie")
public class MovieController extends BaseController
{
    @Autowired
    private IMovieService movieService;

    /**
     * 查询电影列表
     */
    @PreAuthorize("@ss.hasPermi('media:movie:list')")
    @GetMapping("/list")
    public TableDataInfo list(Movie movie)
    {
        startPage();
        List<MovieVO> list = movieService.selectMovieList(movie);
        return getDataTable(list);
    }

    /**
     * 导出电影列表
     */
    @PreAuthorize("@ss.hasPermi('media:movie:export')")
    @Log(title = "电影", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Movie movie)
    {
        List<MovieVO> list = movieService.selectMovieList(movie);
        ExcelUtil<MovieVO> util = new ExcelUtil<MovieVO>(MovieVO.class);
        return util.exportExcel(list, "电影数据");
    }

    /**
     * 获取电影详细信息
     */
    @PreAuthorize("@ss.hasPermi('media:movie:query')")
    @GetMapping(value = "/{movieId}")
    public AjaxResult getInfo(@PathVariable("movieId") Long movieId)
    {
        return AjaxResult.success(movieService.selectMovieById(movieId));
    }

    /**
     * 新增电影
     */
    @PreAuthorize("@ss.hasPermi('media:movie:add')")
    @Log(title = "电影", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MovieVO movieVO)
    {
        int row = movieService.insertMovie(movieVO);
        if(row>0){
            return AjaxResult.success(movieService.selectMovieById(movieVO.getMovieId()));
        }else {
            return AjaxResult.error();
        }
    }

    /**
     * 修改电影
     */
    @PreAuthorize("@ss.hasPermi('media:movie:edit')")
    @Log(title = "电影", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MovieVO movieVO)
    {
        return toAjax(movieService.updateMovie(movieVO));
    }

    /**
     * 删除电影
     */
    @PreAuthorize("@ss.hasPermi('media:movie:remove')")
    @Log(title = "电影", businessType = BusinessType.DELETE)
	@DeleteMapping("/{movieIds}")
    public AjaxResult remove(@PathVariable Long[] movieIds)
    {
        List<Long> ids = Arrays.asList(movieIds);
        return toAjax(movieService.removeByIds(ids));
    }


    /**
     * 上传电影封面
     * @param file
     * @return
     * @throws IOException
     */
    @Log(title = "电影封面", businessType = BusinessType.UPDATE)
    @PostMapping("/upload/images")
    public AjaxResult uploadImages(@RequestParam("file") MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            String imagesUrl = FileUploadUtils.upload(RuoYiConfig.getMovieImagesPath(), file);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", imagesUrl);
            return ajax;
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }


    /**
     * 上传电影视频
     * @param file
     * @return
     * @throws IOException
     */
    @Log(title = "电影视频", businessType = BusinessType.UPDATE)
    @PostMapping("/upload/video")
    public AjaxResult uploadVideo(@RequestParam("file") MultipartFile file) throws IOException, InvalidExtensionException {
        if (!file.isEmpty())
        {
            String imagesUrl = FileUploadUtils.upload(RuoYiConfig.getMovieVideoPath(), file , MimeTypeUtils.MEDIA_EXTENSION);
            String filename = file.getOriginalFilename();
            filename= filename.substring(0,filename.lastIndexOf("."));
            Map<String,Object> data = new HashMap<>(10);
            data.put("url", imagesUrl);
            data.put("title", filename);
            data.put("ext", FileUploadUtils.getExtension(file));
            data.put("filesize",file.getSize());
            AjaxResult ajax = AjaxResult.success(data);
            return ajax;
        }
        return AjaxResult.error("上传视频异常，请联系管理员");
    }
}
