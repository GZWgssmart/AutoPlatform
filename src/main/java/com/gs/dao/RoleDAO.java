package com.gs.dao;

import com.gs.bean.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface RoleDAO extends BaseDAO<String, Role>{

    /**查询除了系统超级管理员之外的管理员*/
    public List<Role> queryCAdminAndSOAdmin();

    /**根据roleName去查询*/
    public Role queryByName(String roleName);

    /**根据userId去查询*/
    public Role queryByUserId(String userId);

    /**查询属于公司的角色*/
    public List<Role> queryByCompanyRole();

    /**查询属于公司的全部角色*/
    public List<Role> queryRole();
}