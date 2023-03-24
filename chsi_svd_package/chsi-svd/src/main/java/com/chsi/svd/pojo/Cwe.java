package com.chsi.svd.pojo;

import com.chsi.framework.pojos.PersistentObject;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SVD_CWE")
public class Cwe extends PersistentObject {
    @Id
    @GeneratedValue(generator = "pk_id")
    @GenericGenerator(name = "pk_id", strategy = "com.chsi.framework.hibernate.StringRandomGenerator")
    @Column(name = "ID", length = 32)
    private String id;

    @Column(name = "cwe_id")
    private String cweId;

    @Column(name = "cwe_name")
    private String desc;
}
