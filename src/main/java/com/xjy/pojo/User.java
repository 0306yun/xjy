package com.xjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 云
 * @create 2023/6/12 11:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String userName;
    private String passWord;
    private String idCard;
    private String phone;
    private String gender;
    private String departmentName;
    private String positionName;
    private String userType;



}
