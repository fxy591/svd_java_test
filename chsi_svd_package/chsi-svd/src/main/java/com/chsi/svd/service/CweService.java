package com.chsi.svd.service;

import com.chsi.svd.pojo.Cwe;
import com.chsi.svd.pojo.PageBean;

import java.util.List;

public interface CweService {
    List<Cwe> queryAllCwe();

    PageBean<Cwe> selectPyPage(Integer currentPage, Integer pageSize);

}
