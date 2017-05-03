package com.gs.service;

import com.gs.bean.Company;
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
    public List<Company> queryByStatusPager(String status, Pager pager);
    public int statusCount(String status);
}