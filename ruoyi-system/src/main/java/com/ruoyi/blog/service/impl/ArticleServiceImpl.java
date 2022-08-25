package com.ruoyi.blog.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
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
import com.ruoyi.common.constant.BaseRedisKeyConstants;
import com.ruoyi.common.constant.BlogConstants;
import com.ruoyi.common.constant.Constants;
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
        redisCache.deleteObject(BaseRedisKeyConstants.HOT_BLOG_ARTICLE);
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
        List<Article> articleList = redisCache.getCacheObject(BaseRedisKeyConstants.HOT_BLOG_ARTICLE);
        if (CollectionUtil.isEmpty(articleList)) {
            articleList = articleMapper.selectHotArticleList(1, 1, top);
            if (CollectionUtil.isNotEmpty(articleList)) {
                redisCache.setCacheObject(BaseRedisKeyConstants.HOT_BLOG_ARTICLE, articleList, BlogConstants.BLOG_ARTICLE_EXPIRATION, TimeUnit.DAYS);
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
        Integer redisJson = redisCache.getCacheObject(BaseRedisKeyConstants.THUMB_BLOG_SUPPORT + ":" + articleId + "#" + userId);
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
            redisCache.setCacheObject(BaseRedisKeyConstants.THUMB_BLOG_SUPPORT + ":" + articleId + "#" + userId, 1);
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

        List<MultipartFile> newFileList = new ArrayList<>();
        List<String> fileNameList = new ArrayList<>();
        for (MultipartFile file : fileList) {
            String fileOriginalName = file.getOriginalFilename();

            if (fileOriginalName.indexOf(".md") > -1) {
                newFileList.add(file);
                // 获取文件名
                fileNameList.add(FilenameUtils.getBaseName(fileOriginalName));
            } else {
                throw new ServiceException("目前仅支持Markdown文件");
            }
        }
        if (newFileList.size() == 0) {
            throw new ServiceException("请选中需要上传的文件");
        }
        // 文档解析
        List<String> fileContentList = new ArrayList<>();
        for (MultipartFile multipartFile : fileList) {
            try (
                    Reader reader = new InputStreamReader(multipartFile.getInputStream(), "utf-8");
                    BufferedReader br = new BufferedReader(reader);
            ) {

                String line;
                StringBuilder content = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    content.append(line + "\n");
                }
                // 将Markdown转换成html
                String articleContent = MarkdownUtils.markdownToHtml(content.toString());
                fileContentList.add(articleContent);
            } catch (Exception e) {
                log.error("文件解析出错:" + e.getMessage());
            }
        }
        String imagesList = ServletUtils.getParameter("imagesList");
        List<Map<String, String>> list = JSON.parseArray(imagesList, Map.class);
        Map<String, String> imagesMap = new HashMap<>();
        for (Map<String, String> item : list) {
            imagesMap.put(item.get("fileOldName"), item.get("url"));
        }
        // 需要替换的图片Map
        Map<String, String> matchUrlMap = new HashMap<>();
        for (String blogContent : fileContentList) {
            List<String> matchList = this.match(blogContent, "<img\\s+(?:[^>]*)src\\s*=\\s*([^>]+)>");
            for (String matchStr : matchList) {
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
                            if (pictureUrl.indexOf(map.getKey()) > -1) {
                                matchUrlMap.put(pictureUrl, map.getValue());
                                break;
                            }
                        }
                    }
                }
            }
        }
        // 开始进行图片替换操作
        WebConfig webConfig = webConfigService.getWebConfig();
        Category category = categoryService.getTopOne();

        int count = 0;
        for (int i = 0; i < fileContentList.size(); i++) {
            try {
                String content = fileContentList.get(i);
                // 循环替换里面的图片
                for (Map.Entry<String, String> map : matchUrlMap.entrySet()) {
                    content = content.replace(map.getKey(), map.getValue());
                }
                Article article = new Article();
                article.setArticlesPart(webConfig.getName());
                article.setLevel(4);
                article.setTitle(fileNameList.get(i));
                article.setSummary(fileNameList.get(i));
                article.setContent(content);
                article.setIsOriginal("1");
                article.setIsPublish("0");
                article.setOpenComment(1);
                article.setType(0);
                article.setStatus(1);
                article.setOpenPassword(0);
                article.setCategoryId(category.getCategoryId() + "");
                article.setCategoryName(category.getName());
                this.insertArticle(article);
                count++;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("本地上传文档失败（替换图片文件失败）" + e.getMessage());
            }
        }
        return count;
    }
}
