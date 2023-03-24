package com.chsi.svd.DTO;

import lombok.Data;

@Data
public class UserLogin {
    private String phone;
    private String email;
    private String password;
    private String code;
}
