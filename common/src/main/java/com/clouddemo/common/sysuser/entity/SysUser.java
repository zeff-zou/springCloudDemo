package com.clouddemo.common.sysuser.entity;

import com.clouddemo.common.base.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zzf on 2017/2/14.
 */
@Entity
@Table(name = "SYS_USER")
public class SysUser extends BaseEntity<Integer> {

    public static final String USER_NAME = "userName";
    public static final String USER_AGE = "userAge";
    public static final String USER_SEX = "userSex";
    public static final String TELEPHONE  = "telephone";

    private String userName;

    private String userAge;

    private String userSex;

    private String telephone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
