package com.blinkfox.learn.mybatis.client;

import com.blinkfox.learn.mybatis.mapper.BlogMapper;
import com.blinkfox.learn.mybatis.pojo.Blog;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mybatis的BlogMapper单元测试类.
 * @author blinkfox on 2017-09-13.
 */
public class BlogMapperTest {

    /** BlogMapper.xml限定名. */
    private static final String BLOG_MAPPER = "mybatis/mybatis-config.xml";

    private static final Logger log = LoggerFactory.getLogger(BlogMapperTest.class);

    /** mybaits的SqlSessionFactory实例. */
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 初始化方法.
     */
    @BeforeClass
    public static void init() {
        // 获取inputStream
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(BLOG_MAPPER);
        } catch (IOException e) {
            log.error("获取BlogMapper.xml文件的输入流失败！", e);
        }
        Assert.assertNotNull(inputStream);

        // 创建SqlSessionFactory实例
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        Assert.assertNotNull(sqlSessionFactory);
    }

    /**
     * 测试根据博客ID获取博客信息的方法.
     */
    @Test
    public void testQueryBlogById() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            Blog blog = blogMapper.queryBlogById(1);
            Assert.assertNotNull(blog);
        }
    }

    /**
     * 测试查询所有博客的部分信息的方法.
     */
    @Test
    public void testQueryBlogs() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            List<Map<String, Object>> blogMaps = blogMapper.queryBlogs();
            Assert.assertNotNull(blogMaps);
        }
    }

    /**
     * 测试根据博客标题模糊查询所有匹配的博客信息的方法.
     */
    @Test
    public void testQueryBlogsByTitle() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            List<Map<String, Object>> blogMaps = blogMapper.queryBlogsByTitle("%My%");
            Assert.assertNotNull(blogMaps);
        }
    }

    /**
     * 销毁实例.
     */
    @AfterClass
    public static void destroy() {
        sqlSessionFactory = null;
    }

}