package com.ruoyi.blog.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.blog.domain.Category;
import com.ruoyi.blog.service.ICategoryService;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.BlogConstants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.MarkdownUtils;
import com.ruoyi.system.util.TokenUtil;
import com.ruoyi.website.domain.WebConfig;
import com.ruoyi.website.service.IWebConfigService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.blog.mapper.ArticleMapper;
import com.ruoyi.blog.domain.Article;
import com.ruoyi.blog.service.IArticleService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 博客文章Service业务层处理
 *
 * @author thh
 * @date 2021-08-28
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    IWebConfigService webConfigService;

    @Autowired
    ICategoryService categoryService;

    /**
     * 查询博客文章
     *
     * @param articleId 博客文章主键
     * @return 博客文章
     */
    @Override
    public Article selectArticleByArticleId(Long articleId) {
        return articleMapper.selectArticleByArticleId(articleId);
    }

    /**
     * 查询博客文章列表
     *
     * @param article 博客文章
     * @return 博客文章
     */
    @Override
    public List<Article> selectArticleList(Article article) {
        return articleMapper.selectArticleList(article);
    }

    @Override
    public List<Article> selectWebArticleList(Article article) {
        article.setIsPublish("1");
        article.setStatus(1);
        return articleMapper.selectWebArticleList(article);
    }

    /**
     * 新增博客文章
     *
     * @param article 博客文章
     * @return 结果
     */
    @Override
    public int insertArticle(Article article) {
        // 初始化数据
        article.setCreateTime(DateUtils.getNowDate());
        article.setSupportCount(0L);
        article.setOpposeCount(0L);
        article.setClickCount(0L);
        article.setCollectCount(0L);
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        article.setAuthor(loginUser.getUser().getNickName());
        return articleMapper.insert(article);
    }

    /**
     * 修改博客文章
     *
     * @param article 博客文章
     * @return 结果
     */
    @Override
    public int updateArticle(Article article) {
        //删除热门文章 防止门户热门文章不同步的情况
        redisCache.deleteObject(CacheConstants.HOT_BLOG_ARTICLE_KEY);
        article.setUpdateTime(DateUtils.getNowDate());
        return articleMapper.updateById(article);
    }

    /**
     * 批量删除博客文章
     *
     * @param articleIds 需要删除的博客文章主键
     * @return 结果
     */
    @Override
    public int deleteArticleByArticleIds(Long[] articleIds) {
        return articleMapper.deleteArticleByArticleIds(articleIds);
    }

    /**
     * 删除博客文章信息
     *
     * @param articleId 博客文章主键
     * @return 结果
     */
    @Override
    public int deleteArticleByArticleId(Long articleId) {
        return articleMapper.deleteById(articleId);
    }

    @Override
    public List<Article> selectHotArticleList(int top) {
        List<Article> articleList = redisCache.getCacheObject(CacheConstants.HOT_BLOG_ARTICLE_KEY);
        if (CollectionUtil.isEmpty(articleList)) {
            articleList = articleMapper.selectHotArticleList(1, 1, top);
            if (CollectionUtil.isNotEmpty(articleList)) {
                redisCache.setCacheObject(CacheConstants.HOT_BLOG_ARTICLE_KEY, articleList, BlogConstants.BLOG_ARTICLE_EXPIRATION, TimeUnit.DAYS);
            }
        }
        return articleList;
    }

    @Override
    public List<Article> selectNewArticleList() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("is_publish", "1");
        queryWrapper.orderByDesc("create_time");
        //因为首页并不需要显示内容，所以需要排除掉内容字段
        queryWrapper.select(Article.class, i -> !i.getProperty().equals("content") && !i.getProperty().equals("password"));
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    public List<Article> selectSearchArticleList(Article article) {
        article.setIsPublish("1");
        article.setStatus(1);
        return articleMapper.selectSearchArticleList(article);
    }

    @Override
    public Article selectWebArticleByArticleId(Long articleId) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        queryWrapper.eq("status", 1);
        queryWrapper.eq("is_publish", "1");
        queryWrapper.select(Article.class, i -> !i.getProperty().equals("password"));
        return articleMapper.selectOne(queryWrapper);
    }

    @Override
    public void thumbArticleById(Long articleId) {
        Long userId = SecurityUtils.getUserId();
        Integer redisJson = redisCache.getCacheObject(CacheConstants.THUMB_BLOG_SUPPORT_KEY + articleId + "#" + userId);
        if (redisJson != null) {
            throw new ServiceException("24小时内无法重复点赞同一篇文章");
        } else {
            Article article = articleMapper.selectById(articleId);
            Long supportCount = article.getOpposeCount();
            if (supportCount == null) {
                supportCount = 0L;
            }
            article.setSupportCount(supportCount + 1);
            articleMapper.updateById(article);
            redisCache.setCacheObject(CacheConstants.THUMB_BLOG_SUPPORT_KEY + articleId + "#" + userId, 1);
        }
    }

    @Override
    public List<Article> getSameArticleList(Long articleId) {
        Article article = this.selectWebArticleByArticleId(articleId);
        String categoryId = article.getCategoryId();
        article.setCategoryId(categoryId);
        article.setStatus(1);
        article.setIsPublish("1");
        Page<Article> page = new Page<>(1, 10);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.eq("is_publish", "1");
        queryWrapper.notIn("article_id", articleId);
        queryWrapper.orderByDesc("create_time");
        return articleMapper.selectPage(page, queryWrapper).getRecords();
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private List<String> match(String str, String regex) {
        if (null == str) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        List<String> list = new LinkedList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }


    @Override
    public int uploadLocalBlog(List<MultipartFile> fileList) {
        // 网站配置
        WebConfig webConfig = webConfigService.getWebConfig();
        // 查询最热门的分类
        Category category = categoryService.getTopOne();
        // 如果设置分类，抛异常或者给个null值
        if (category == null) {
            category = new Category();
            category.setCategoryId(null);
            category.setName(null);
        }

        String html;
        // 0.把上传的图片list 转化成map key:原文件名称（originalFilenames） value: 图片链接地址（url）
        Map<String, String> imagesMap = getUploadImageMap();
        int count = 0;
        for (MultipartFile multipartFile : fileList) {
            String artileTitle = FilenameUtils.getBaseName(multipartFile.getOriginalFilename());
            // 1.校验 Markdown文件
            verifyBlog(multipartFile);
            // 2.文档解析,把每个markdown文件解析成html
            html = parseArticle2Html(multipartFile);
            // 4.文章内容 匹配图片正则 "<img\\s+(?:[^>]*)src\\s*=\\s*([^>]+)>"
            List<String> matchImageList = this.match(html, "<img\\s+(?:[^>]*)src\\s*=\\s*([^>]+)>");
            // 匹配图片map key：markdown里的图片url，  value：图片链接
            Map<String, String> matchUrlMap = getMatchUrlMap(matchImageList, imagesMap);
            // 5. 文章内容，全局替换图片
            // 循环替换里面的图片
            for (Map.Entry<String, String> map : matchUrlMap.entrySet()) {
                html = html.replace(map.getKey(), map.getValue());
            }
            saveArticle(webConfig, category, html, artileTitle);
            count++;
        }
        return count;
    }

    private void saveArticle(WebConfig webConfig, Category category, String html, String artileTitle) {
        Article article = new Article();
        article.setArticlesPart(webConfig.getName());
        article.setLevel(4);
        article.setTitle(artileTitle);
        article.setSummary(artileTitle);
        article.setContent(html);
        article.setIsOriginal("1");
        article.setIsPublish("0");
        article.setOpenComment(1);
        article.setType(0);
        article.setStatus(1);
        article.setOpenPassword(0);
        article.setCategoryId(category.getCategoryId() + "");
        article.setCategoryName(category.getName());
        this.insertArticle(article);
    }

    private Map<String, String> getMatchUrlMap(List<String> matchImageList, Map<String, String> imagesMap) {
        Map<String, String> matchUrlMap = new HashMap<>();
        for (String matchStr : matchImageList) {
            String[] splitList = matchStr.split("\"");
            // 取出中间的图片
            if (splitList.length >= 5) {
                // alt 和 src的先后顺序
                // 得到具体的图片路径
                String pictureUrl = "";
                if (matchStr.indexOf("alt") > matchStr.indexOf("src")) {
                    pictureUrl = splitList[1];
                } else {
                    pictureUrl = splitList[3];
                }
                // 判断是网络图片还是本地图片
                if (!pictureUrl.startsWith("http")) {
                    // 那么就需要遍历全部的map和他匹配
                    for (Map.Entry<String, String> map : imagesMap.entrySet()) {
                        // 查看Map中的图片是否在需要替换的key中
                        if (pictureUrl.contains(map.getKey())) {
                            matchUrlMap.put(pictureUrl, map.getValue());
                            break;
                        }
                    }
                }
            }
        }
        return matchUrlMap;
    }

    // 获取上传图片list
    private Map<String, String> getUploadImageMap() {
        String imagesList = ServletUtils.getParameter("imagesList");
        List<Map> list = JSON.parseArray(imagesList, Map.class);
        Map<String, String> imagesMap = new HashMap<>();
        for (Map item : list) {
            imagesMap.put(MapUtils.getString(item, "originalFilenames"), MapUtils.getString(item, "url"));
        }
        return imagesMap;
    }

    /**
     * 解析文章
     *
     * @param multipartFile
     * @return java.lang.String
     */
    private String parseArticle2Html(MultipartFile multipartFile) {
        try (
                Reader reader = new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(reader);
        ) {

            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            // 将Markdown转换成html
            return MarkdownUtils.markdownToHtml(content.toString());
        } catch (Exception e) {
            log.error("文件解析出错:" + e.getMessage());
            throw new ServiceException("文件解析出错,无法转化为html");
        }
    }

    /**
     * 校验文章
     *
     * @param multipartFile
     */
    private void verifyBlog(MultipartFile multipartFile) {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (extension == null || extension.endsWith(".md")) {
            throw new ServiceException("目前仅支持Markdown文件");
        }
    }
}