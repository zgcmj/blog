package com.cmj.blog.web.service;

import com.cmj.blog.doamin.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    List<Type> getBlogType();  //首页右侧展示type对应的博客数量

    int updateType(Type type);
    int deleteType(Long id);

}
