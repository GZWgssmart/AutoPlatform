package com.gs.dao;

import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
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

    public List<User> queryByUser(@Param("pager")Pager pager, @Param("companyId")String companyId);

    public int countByUser(String companyId);

    /*查询所有管理员 */
    public List<User> queryByAdminPager(Pager pager);

    /*统计所有管理员的个数*/
    public int  countAdmin();

    /*查询汽修公司管理员 */
    public List<User> queryByCompanyAdminPager(Pager pager);

    /*统计汽修公司管理员的个数*/
    public int  countCompanyAdmin();

    /*查询系统管理员 */
    public List<User> queryBySystemAdminPager(Pager pager);

    /*统计系统管理员的个数*/
    public int  countSystemAdmin();

    /*分页查询所有车主*/
    public List<User> queryCustomerPager(Pager pager);

    /*分页查询所有员工*/
    public List<User> queryPeoplePager(Pager pager);

    /*登陆*/
    public User queryLogin(User user);


    /**添加管理员*/
    public void insertAdmin(User user);
    /*根据手机号查询id*/
    public User queryByPhone(User user);
}