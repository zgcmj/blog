package com.cmj.blog.dao;

import com.cmj.blog.doamin.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagDao {
    int saveTag(@Param("tag")Tag tag);

    Tag getTag(@Param("id")Long id);

    Tag getTagByName(@Param("name")String name);

    List<Tag> getAllTag();

    List<Tag> getBlogTag();  //首页展示博客标签

    int updateTag(@Param("tag")Tag tag);

    int deleteTag(@Param("id")Long id);
}
