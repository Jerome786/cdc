package com.cdc.train.entity;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-08-25 15:38:25
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -58878653668468300L;

    private Integer roleId;

    private String name;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}