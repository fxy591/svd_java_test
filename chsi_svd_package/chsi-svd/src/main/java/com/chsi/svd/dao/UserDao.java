package com.chsi.svd.dao;

import com.chsi.svd.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> selectByPageAndCondition(int begin, int size, User user);
    void add(User user);
    void update(User user);
    void delete(User user);
    int selectTotalByCondition(User user);
    int selectTotal();
    List<User> selectByPage(int begin, int size);
    User selectById(String id);
    User select(String pore, String value);
}
