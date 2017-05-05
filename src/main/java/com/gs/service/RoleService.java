package com.gs.service;

import com.gs.bean.Role;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface RoleService extends BaseService<String, Role>{

    /**查询除了系统超级管理员之外的管理员*/
    public List<Role> queryCAdminAndSOAdmin();

    /**根据roleName去查询*/
    public Role queryByName(String roleName);

    /**根据userId去查询*/
    public Role queryByUserId(String userId);
}