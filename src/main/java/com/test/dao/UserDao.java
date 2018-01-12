package com.test.dao;
import com.test.entity.User;

/**
 * Created by Doublestar on 2017/11/24 11:16).
 */

/**
 * DAO接口,mybatis动态完成Impl
 */

public interface UserDao {
    // 根据用户名查找用户登录相关信息
    public User findByName(String name);
    // 添加用户
    public void addUser(User user);
}
