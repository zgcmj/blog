package com.cmj.blog.dao;

import com.cmj.blog.doamin.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface TypeDao {
    int saveType(@Param("type")Type type);

    Type getType(@Param("id")Long id);

    Type getTypeByName(@Param("name")String name);

    List<Type> getAllType();

    List<Type> getBlogType();  //首页右侧展示type对应的博客数量

    int updateType(@Param("type") Type type);
    int deleteType(@Param("id") Long id);

}
