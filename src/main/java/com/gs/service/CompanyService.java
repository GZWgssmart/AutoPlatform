package com.gs.service;

import com.gs.bean.Company;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface CompanyService extends BaseService<String, Company>{
    public List<Company> queryByStatusPager(@Param("status")String status, @Param("pager")Pager pager,@Param("user")User user);
    public int statusCount(@Param("status")String status,@Param("user")User user);
    public List<Company> searchByPager(@Param("companyName")String companyName, @Param("userName")String userName,@Param("pager")Pager pager);
    public int searchCount(@Param("companyName") String companyName,@Param("userName") String userName);
}