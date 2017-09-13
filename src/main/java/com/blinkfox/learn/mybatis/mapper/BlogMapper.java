package com.blinkfox.learn.mybatis.mapper;

import com.blinkfox.learn.mybatis.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 博客的mybatis的接口.
 * @author blinkfox on 2017-09-13.
 */
public interface BlogMapper {

    /**
     * 根据博客ID查询博客实体信息.
     * @param id 博客ID
     * @return 博客实体信息
     */
    Blog queryBlogById(int id);

    /**
     * 查询所有博客信息.
     * @return map的List集合.
     */
    List<Map<String, Object>> queryBlogs();

    /**
     * 根据博客标题模糊查询所有匹配的博客信息.
     * @param title 博客标题
     * @return map的List集合.
     */
    List<Map<String, Object>> queryBlogsByTitle(@Param("title") String title);

}