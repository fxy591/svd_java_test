package com.chsi.svd.pojo;

import com.chsi.framework.pojos.PersistentObject;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SVD_USER")
public class User extends PersistentObject {
    @Id
    @GeneratedValue(generator = "pk_id")
    @GenericGenerator(name = "pk_id", strategy = "com.chsi.framework.hibernate.StringRandomGenerator")
    @Column(name = "ID", length = 32)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password = "123456";

    @Column(name = "DEPART")
    private String depart;

    @Column(name = "POSITION")
    private String position;

    // 角色 1超管 2部管 3普通员工
    @Column(name = "ROLE")
    private int role;

    // 通知方式 1 邮箱 2 手机号
    @Column(name = "NOTICE")
    private int notice;

    // 每周订阅 1 订阅 2不订阅
    @Column(name = "MZDY")
    private int mzdy;

    // 每月订阅 1 订阅 2 不订阅
    @Column(name = "MYDY")
    private int mydy;

    @Transient
    private boolean editFlag = false;
}
