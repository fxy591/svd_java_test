package com.chsi.svd.DTO;

import com.chsi.svd.pojo.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private List<User> users = new ArrayList<>();
}
