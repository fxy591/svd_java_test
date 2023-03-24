package com.chsi.svd.dao.hibernate;

import com.chsi.framework.hibernate.BaseHibernateDAO;
import com.chsi.svd.dao.CveDao;
import com.chsi.svd.pojo.Cve;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import java.util.List;

public class CveDaoImpl extends BaseHibernateDAO implements CveDao {

    /**
     * 根据cve编号获取cve详细信息
     * @param cveName
     * @return
     */
    @Override
    public Cve queryMatchCve(String cveName) {
        String sql = "select * from SVD_CVE_LIST where CVE_NAME = :cveName";
        SQLQuery sqlQuery =  this.hibernateUtil.getSession().createSQLQuery(sql);
        sqlQuery.setParameter("cveName", cveName);
        sqlQuery.addEntity(Cve.class);
        Cve cve = (Cve)sqlQuery.uniqueResult();
        return cve;
    }

    @Override
    public int queryLevelNum(String name, String level) {
        String sql = "select count(*) from SVD_CVE_LIST where CVE_" + name + "_SCORE like '%"+ level +"%' ";
        Query query = this.hibernateUtil.getSession().createSQLQuery(sql);
        return Integer.parseInt(query.uniqueResult().toString());

        // return null;
    }

    @Override
    public int queryNullNum(String name) {
        String sql = "SELECT count(*) from SVD_CVE_LIST where CVE_" + name + "_SCORE is null";
        Query query = this.hibernateUtil.getSession().createSQLQuery(sql);
        return Integer.parseInt(query.uniqueResult().toString());
    }

    @Override
    public List<Cve> queryTodayCve(String today) {
        String sql = "select * from SVD_CVE_LIST where CVE_LAST_MODIFIED = :today";
        SQLQuery sqlQuery =  this.hibernateUtil.getSession().createSQLQuery(sql);
        sqlQuery.setParameter("today", today);
        sqlQuery.addEntity(Cve.class);

        return sqlQuery.list();
    }

}
