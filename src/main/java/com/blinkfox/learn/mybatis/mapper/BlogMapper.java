package com.blinkfox.learn.mybatis.mapper;

import java.util.Map;

/**
 * 博客的mybatis的接口.
 * @author blinkfox on 2017-09-13.
 */
public interface BlogMapper {

    /**
     * 根据博客ID查询博客实体信息.
     * @param id 博客ID
     * @return Map结构的博客实体信息
     */
    Map<String, Object> queryBlogById(int id);

}