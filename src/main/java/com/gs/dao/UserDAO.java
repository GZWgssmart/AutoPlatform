package com.gs.dao;

import com.gs.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface UserDAO extends BaseDAO<String, User>{

    public List<User> queryByUser(String companyId);
}