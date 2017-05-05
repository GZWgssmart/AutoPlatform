package com.gs.service;

import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface UserService extends BaseService<String, User>{

    public List<User> queryByUser(Pager pager, String companyId);

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
    public List<User> queryPeoplePager(Pager pager, String companyId);

    /*登陆*/
    public User queryLogin(User user);

    /**添加管理员*/
    public void insertAdmin(User user);

    /*根据手机号查询id*/
    public User queryByPhone(String phone);

    /**更新最后一次登陆的时间*/
    public void updateLoginTime(String userId);

    /**统计当前登陆者公司的所有员工*/
    public int countCompanyEmp(String companyId);

}