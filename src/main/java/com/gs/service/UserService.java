package com.gs.service;

import com.gs.bean.User;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface UserService extends BaseService<String, User>{

    public List<User> queryByUser(String companyId);

    public int countByUser(String companyId);
}