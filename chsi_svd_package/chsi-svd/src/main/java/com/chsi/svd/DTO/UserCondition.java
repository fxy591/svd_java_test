package com.chsi.svd.DTO;

import lombok.Data;

@Data
public class UserCondition {
    private String name;
    private String phone;
    private String email;
    private String depart;
    private String position;
    private int currentPage;
    private int pageSize;
}
