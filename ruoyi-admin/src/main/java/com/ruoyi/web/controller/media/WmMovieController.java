package com.ruoyi.web.controller.media;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.FileTypeUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.midea.domain.WmMovie;
import com.ruoyi.midea.service.IWmMovieService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 电影管理Controller
 *
 * @author thh
 * @date 2021-05-01
 */
@RestController
@RequestMapping("/media/movie")
public class WmMovieController extends BaseController
{
    @Autowired
    private IWmMovieService wmMovieService;

    /**
     * 查询电影管理列表
     */
    @PreAuthorize("@ss.hasPermi('media:movie:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmMovie wmMovie)
    {
        startPage();
        List<WmMovie> list = wmMovieService.selectWmMovieList(wmMovie);
        return getDataTable(list);
    }

    /**
     * 导出电影管理列表
     */
    @PreAuthorize("@ss.hasPermi('media:movie:export')")
    @Log(title = "电影管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WmMovie wmMovie)
    {
        List<WmMovie> list = wmMovieService.selectWmMovieList(wmMovie);
        ExcelUtil<WmMovie> util = new ExcelUtil<WmMovie>(WmMovie.class);
        return util.exportExcel(list, "电影管理数据");
    }

    /**
     * 获取电影管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('media:movie:query')")
    @GetMapping(value = "/{movieId}")
    public AjaxResult getInfo(@PathVariable("movieId") Long movieId)
    {
        return AjaxResult.success(wmMovieService.selectWmMovieById(movieId));
    }

    /**
     * 新增电影管理
     */
    @PreAuthorize("@ss.hasPermi('media:movie:add')")
    @Log(title = "电影管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmMovie wmMovie)
    {
        return toAjax(wmMovieService.insertWmMovie(wmMovie));
    }

    /**
     * 修改电影管理
     */
    @PreAuthorize("@ss.hasPermi('media:movie:edit')")
    @Log(title = "电影管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmMovie wmMovie)
    {
        return toAjax(wmMovieService.updateWmMovie(wmMovie));
    }

    /**
     * 删除电影管理
     */
    @PreAuthorize("@ss.hasPermi('media:movie:remove')")
    @Log(title = "电影管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{movieIds}")
    public AjaxResult remove(@PathVariable Long[] movieIds)
    {
        return toAjax(wmMovieService.deleteWmMovieByIds(movieIds));
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
