package com.chsi.svd.service.Impl;

import com.chsi.framework.service.BaseDbService;
import com.chsi.svd.dao.CweDao;
import com.chsi.svd.pojo.Cwe;
import com.chsi.svd.pojo.PageBean;
import com.chsi.svd.service.CweService;
import java.util.List;

public class CweServiceImpl extends BaseDbService implements CweService {

    private CweDao cweDao;
    @Override
    protected void doCreate() {
        cweDao = getDAO("cweDao", CweDao.class);
    }

    @Override
    protected void doRemove() {

    }

    /**
     * 获取所有漏洞类型list
     * @return
     */
    @Override
    public List<Cwe> queryAllCwe() {
        System.out.println("123");
        return cweDao.queryAllCwe();
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Cwe> selectPyPage(Integer currentPage, Integer pageSize) {
        int begin = (currentPage - 1) * pageSize;
        int total = cweDao.selectTotal();
        List<Cwe> cweList = cweDao.selectByPage(begin, pageSize);
        PageBean<Cwe> pageBean = new PageBean<Cwe>();
        pageBean.setRows(cweList);
        pageBean.setTotalCount(total);
        return pageBean;
    }

}
