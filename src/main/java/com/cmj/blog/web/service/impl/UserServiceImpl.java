package com.cmj.blog.web.service.impl;

import com.cmj.blog.dao.UserDao;
import com.cmj.blog.doamin.User;
import com.cmj.blog.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        return userDao.queryByUsernameAndPassword(username,password);
    }
}
