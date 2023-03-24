package com.chsi.svd.service.Impl;

import com.chsi.framework.service.BaseDbService;
import com.chsi.svd.dao.UserDao;
import com.chsi.svd.pojo.PageBean;
import com.chsi.svd.pojo.User;
import com.chsi.svd.service.UserService;

import java.util.List;

public class UserServiceImpl extends BaseDbService implements UserService {

    private UserDao userDao;

    @Override
    protected void doCreate() {
        userDao = getDAO("userDao", UserDao.class);
    }

    @Override
    protected void doRemove() {

    }

    @Override
    public PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user) {
        int begin = (currentPage-1) * pageSize;

        List<User> rows = userDao.selectByPageAndCondition(begin, pageSize, user);
        int total = userDao.selectTotalByCondition(user);

        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setTotalCount(total);
        pageBean.setRows(rows);

        return pageBean;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public PageBean<User> selectByPage(int currentPage, int pageSize) {
        int begin = (currentPage-1) * pageSize;

        List<User> rows = userDao.selectByPage(begin, pageSize);
        int total = userDao.selectTotal();

        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setTotalCount(total);
        pageBean.setRows(rows);

        return pageBean;
    }

    @Override
    public User selectById(String id) {
        return userDao.selectById(id);
    }

    @Override
    public User select(String pore, String value) {
        return userDao.select(pore,value);
    }
}
