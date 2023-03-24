package com.chsi.svd.service.Impl;

import com.chsi.framework.service.BaseDbService;
import com.chsi.svd.DTO.PieData;
import com.chsi.svd.dao.CveDao;
import com.chsi.svd.pojo.Cve;
import com.chsi.svd.service.CveService;

import java.util.List;

public class CveServiceImpl extends BaseDbService implements CveService {

    private CveDao cveDao;
    @Override
    protected void doCreate() {
        cveDao = getDAO("cveDao", CveDao.class);
    }

    @Override
    protected void doRemove() {

    }

    /**
     * 传入cve编号获取详细信息
     * @param cveName
     * @return
     */
    @Override
    public Cve queryMatchCve(String cveName) {
        // cveName = cveName.substring(1, cveName.length()-1);
        // System.out.println(cveName);
        Cve cve = cveDao.queryMatchCve(cveName);
        return cve;
    }

    @Override
    public PieData queryLevelNum(String name, String level) {
        int num = cveDao.queryLevelNum(name,level);
        PieData pieData = new PieData();
        pieData.setName(level);
        pieData.setValue(num);
        return pieData;
    }

    @Override
    public PieData queryNullNum(String name) {
        int num = cveDao.queryNullNum(name);
        PieData pieData = new PieData();
        pieData.setName("UNDEFINED");
        pieData.setValue(num);
        return pieData;
    }

    @Override
    public List<Cve> queryTodayCve(String today) {
        return cveDao.queryTodayCve(today);
    }
}
