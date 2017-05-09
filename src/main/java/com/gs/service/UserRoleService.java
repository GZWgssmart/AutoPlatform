package com.gs.service;

import com.gs.bean.UserRole;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface UserRoleService extends BaseService<String, UserRole>{

    public void updateByRole(UserRole userRole);

}