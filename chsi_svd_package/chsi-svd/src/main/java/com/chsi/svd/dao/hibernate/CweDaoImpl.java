package com.chsi.svd.dao.hibernate;

import com.chsi.framework.hibernate.BaseHibernateDAO;
import com.chsi.svd.dao.CweDao;
import com.chsi.svd.pojo.Cwe;
import org.hibernate.Query;

import java.util.List;

public class CweDaoImpl extends BaseHibernateDAO implements CweDao {
    /**
     * 返回所有的漏洞类型
     * @return
     */
    @Override
    public List<Cwe> queryAllCwe() {
        String hql = "from Cwe";
        Query query = this.hibernateUtil.getSession().createQuery(hql);
        List<Cwe> list = query.list();
        return list;
    }

    /**
     * 查询类型总数
     * @return
     */
    @Override
    public int selectTotal() {
        String sql = "select count(*) from SVD_CWE";
        Query query = this.hibernateUtil.getSession().createSQLQuery(sql);
        return Integer.parseInt(query.uniqueResult().toString());
    }

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Override
    public List<Cwe> selectByPage(Integer begin, Integer size) {
        String hql = "from Cwe";
        Query query = this.hibernateUtil.getSession().createQuery(hql);
        query.setMaxResults(size);
        query.setFirstResult(begin);
        return query.list();
    }


}
