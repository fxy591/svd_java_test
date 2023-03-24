package com.chsi.svd.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
    // 总记录数
    private int totalCount;
    // 当前页面数据
    private List<T> rows;
}
