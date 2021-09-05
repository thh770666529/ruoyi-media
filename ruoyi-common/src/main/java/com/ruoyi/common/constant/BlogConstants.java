package com.ruoyi.common.constant;

/**
 * @ClassName BlogConstants
 * @Description 博客相关常量
 * @Author thh
 * @Date 11:40 2021/09/04
 **/
public class BlogConstants {

    /**
     * 博客文章缓存有效期（天）
     */
    public static final Integer BLOG_ARTICLE_EXPIRATION = 1;

    /**
     * 博客标签缓存有效期（天）
     */
    public static final Integer BLOG_TAG_EXPIRATION = 1;

    public static final String ORIGINAL_TEMPLATE = "本文为辉皇影院原创文章，转载无需和我联系，但请注明来自辉皇影院 http://www.ithhit.cn";

    public static final String REPRINTED_TEMPLATE = "本着开源共享、共同学习的精神，本文转载自 %S ，版权归 %S 所有，如果侵权之处，请联系博主进行删除，谢谢~";
}
