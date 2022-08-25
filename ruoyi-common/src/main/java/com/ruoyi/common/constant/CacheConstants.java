package com.ruoyi.common.constant;

/**
 * 缓存的key 常量
 * 
 * @author ruoyi
 */
public class CacheConstants
{
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

    /**
     * 网站站点配置 cache key
     */
    public static final String WEB_CONFIG_KEY = "web_config:";


    /**
     * 博客点击
     */
    public final static String BLOG_CLICK_KEY = "BLOG_CLICK:";


    /**
     * 最热博客文章
     */
    public final static String HOT_BLOG_ARTICLE_KEY = "HOT_BLOG_ARTICLE:";

    /**
     * 支持博客文章
     */
    public final static String THUMB_BLOG_SUPPORT_KEY = "THUMB_BLOG_SUPPORT:";

    /**
     * 最热博客标签
     */
    public final static String HOT_BLOG_TAG_KEY = "HOT_BLOG_TAG:";


    /**
     * 电影点击
     */
    public final static String MOVIE_CLICK_KEY = "MOVIE_CLICK:";


    /**
     * 演员点击
     */
    public final static String ACTOR_CLICK_KEY = "ACTOR_CLICK:";


    /**
     * 每日用户积分
     */
    public final static String USER_INTEGRAL_ADD_LIMIT_KEY = "USER_INTEGRAL_ADD_LIMIT:";
}
