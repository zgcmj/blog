package com.cmj.blog.dao;

import com.cmj.blog.doamin.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

     User queryByUsernameAndPassword(@Param("username")String username,@Param("password") String password);
}
