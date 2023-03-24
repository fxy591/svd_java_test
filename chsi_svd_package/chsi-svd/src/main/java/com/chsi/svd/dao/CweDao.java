package com.chsi.svd.dao;

import com.chsi.svd.pojo.Cwe;

import java.util.List;

public interface CweDao {
    List<Cwe> queryAllCwe();

    int selectTotal();

    List<Cwe> selectByPage(Integer begin, Integer size);
}
