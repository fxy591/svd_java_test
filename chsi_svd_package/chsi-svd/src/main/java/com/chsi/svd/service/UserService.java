package com.chsi.svd.service;

import com.chsi.svd.pojo.PageBean;
import com.chsi.svd.pojo.User;

public interface UserService {
    PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user);
    void add(User user);
    void update(User user);
    void delete(User user);
    PageBean<User> selectByPage(int currentPage, int pageSize);
    User selectById(String id);
    User select(String pore, String value);

}
