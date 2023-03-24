package com.chsi.svd.dao.hibernate;

import com.chsi.framework.hibernate.BaseHibernateDAO;
import com.chsi.svd.dao.UserDao;
import com.chsi.svd.pojo.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl extends BaseHibernateDAO implements UserDao {


    @Override
    public List<User> selectByPageAndCondition(int begin, int size, User user) {
        String sql = "from User as u where 1=1 ";
        if (user.getName() != null && user.getName() != "") {
            sql = sql + " and u.name like '%" + user.getName() + "%' ";
        }
        if (user.getPhone() != null && user.getPhone() != "") {
            sql = sql + " and u.phone like '%" + user.getPhone() + "%' ";
        }
        if (user.getEmail() != null && user.getEmail() != "") {
            sql = sql + " and u.email like '%" + user.getEmail() + "%' ";
        }
        if (user.getDepart() != null && user.getDepart() != "") {
            sql = sql + " and u.depart like '%" + user.getDepart() + "%' ";
        }
        if (user.getPosition() != null && user.getPosition() != "") {
            sql = sql + " and u.position like '%" + user.getPosition() + "%' ";
        }

        Query query = this.hibernateUtil.getSession().createQuery(sql);
        query.setFirstResult(begin);
        query.setMaxResults(size);

        return query.list();
    }

    @Override
    public void add(User user) {
        Session session = this.hibernateUtil.getSession();
        session.save(user);
    }

    @Override
    public void update(User user) {
        // String sql = "update SVD_USER set NAME=:name, PHONE=:phone, EMAIL=:email, PASSWORD=:password, DEPART=:depart, POSITION=:position, ROLE=:role, NOTICE=notice:, MZDY=:mzyd, MYDY=:mydy where ID=:id";
        // Query sqlQuery = this.hibernateUtil.getSession().createSQLQuery(sql);
        // sqlQuery.setParameter("id", user.getId());
        // sqlQuery.setParameter("name", user.getName());
        // sqlQuery.setParameter("phone", user.getPhone());
        // sqlQuery.setParameter("password", user.getPassword());
        // sqlQuery.setParameter("depart", user.getDepart());
        // sqlQuery.setParameter("position", user.getPosition());
        // sqlQuery.setParameter("role", user.getRole());
        // sqlQuery.setParameter("notice", user.getNotice());
        // sqlQuery.setParameter("mzdy", user.getMzdy());
        // sqlQuery.setParameter("mydy", user.getMydy());
        //
        // sqlQuery.executeUpdate();

        this.hibernateUtil.getSession().update(user);
    }

    @Override
    public void delete(User user) {
        this.hibernateUtil.getSession().delete(user);
    }

    @Override
    public int selectTotalByCondition(User user) {
        String sql = "select count(*) from SVD_USER where 1=1 ";
        if (user.getName() != null && user.getName() != "") {
            sql = sql + " and NAME like '%" + user.getName() + "%' ";
        }
        if (user.getPhone() != null && user.getPhone() != "") {
            sql = sql + " and PHONE like '%" + user.getPhone() + "%' ";
        }
        if (user.getEmail() != null && user.getEmail() != "") {
            sql = sql + " and EMAIL like '%" + user.getEmail() + "%' ";
        }
        if (user.getDepart() != null && user.getDepart() != "") {
            sql = sql + " and DEPART like '%" + user.getDepart() + "%' ";
        }
        if (user.getPosition() != null && user.getPosition() != "") {
            sql = sql + " and POSITION like '%" + user.getPosition() + "%' ";
        }

        Query query = this.hibernateUtil.getSession().createSQLQuery(sql);
        return Integer.parseInt(query.uniqueResult().toString());

    }

    @Override
    public int selectTotal() {
        String sql = "select count(*) from SVD_USER where 1=1 ";
        Query query = this.hibernateUtil.getSession().createSQLQuery(sql);
        return Integer.parseInt(query.uniqueResult().toString());
    }

    @Override
    public List<User> selectByPage(int begin, int size) {
        String hql = "from User";
        Query query = this.hibernateUtil.getSession().createQuery(hql);
        query.setFirstResult(begin);
        query.setMaxResults(size);
        return query.list();
    }

    @Override
    public User selectById(String id) {
        String hql = "from User where id = :id";
        Query query = this.hibernateUtil.getSession().createQuery(hql);
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }

    @Override
    public User select(String pore, String value) {
        String hql = "from User where " + pore + " = " + value;
        Query query = this.hibernateUtil.getSession().createQuery(hql);
        return (User) query.uniqueResult();
    }
}
