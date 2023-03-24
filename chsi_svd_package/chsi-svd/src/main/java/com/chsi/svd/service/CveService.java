package com.chsi.svd.service;

import com.chsi.svd.DTO.PieData;
import com.chsi.svd.pojo.Cve;

import java.util.List;

public interface CveService {
    Cve queryMatchCve(String cveName);

    PieData queryLevelNum(String name, String level);

    PieData queryNullNum(String name);

    List<Cve> queryTodayCve(String today);
}
