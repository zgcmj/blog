package com.cmj.blog.web.service.impl;

import com.cmj.blog.dao.TypeDao;
import com.cmj.blog.doamin.Type;
import com.cmj.blog.web.service.TypeService;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;


    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public List<Type> getBlogType() {

        return typeDao.getBlogType();
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}
