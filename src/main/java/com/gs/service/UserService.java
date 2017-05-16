package com.gs.service;

import com.gs.bean.Company;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
    public List<User> queryCustomerPager(Pager pager, User user);

    /*分页查询所有员工*/
    public List<User> queryPeoplePager(Pager pager, User user);

    /*登陆*/
    public User queryLogin(User user);

    /**添加管理员*/
    public void insertAdmin(User user);

    /*根据手机号查询id*/
    public User queryByPhone(String phone);

    /**更新最后一次登陆的时间*/
    public void updateLoginTime(String userId);

    /**统计当前登陆者公司的所有员工*/
    public int countCompanyEmp(User user);

    /*验证手机号*/
    public int queryPhone(String userPhone);

    /**验证邮箱*/
    public int queryEmail(String userEmail);

    /*验证身份证*/
    public int queryIdentity(String userIdentity);

    /**修改管理员*/
    public void updateAdmin(User user);

    /**条件查询管理员*/
    public List<User> selectQuery(Pager pager, String userName, String userPhone, String userEmail);

    /**统计条件查询管理员的个数*/
    public int countSelectAdmin(String userName, String userPhone, String userEmail);

    /*查询自己公司的员工*/
    public List<User> queryUser(String companyId);

    /*分页查询所有不可用车主个数*/
    public int countStatus(User user);

    /*分页查询所有不可用车主*/
    public List<User> queryCustomerPagerStatus(Pager pager, User user);

    /*分页查询所有车主个数*/
    public int countCustomer(User user);

    /*分页查询所有车主*/
    public List<User> queryCustomer(Pager pager, User user);

    /*条件查询车主个数*/
    public int selectCountCustomer(User user);

    /*条件查询车主*/
    public List<User> selectCustomer(Pager pager, User user);

    /*分页查询所有不可用员工个数*/
    public int countStatusEmp(User user);

    /*分页查询所有不可用员工*/
    public List<User> queryPeoplePagerStatus(Pager pager, User user);

    /*分页查询所有员工个数*/
    public int countAllEmp(User user);

    /*分页查询所有员工*/
    public List<User> queryPeoplePagerAll(Pager pager, User user);

    /*条件查询员工个数*/
    public int countSelectQueryEmp(User user, Role role, Company company);

    /*条件查询员工*/
    public List<User> selectQueryEmp(Pager pager, User user, Role role, Company company);

}