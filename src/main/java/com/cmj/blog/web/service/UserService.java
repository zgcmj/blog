package com.cmj.blog.web.service;

import com.cmj.blog.doamin.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User checkUser(String username, String password);
}
