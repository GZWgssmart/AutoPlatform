package com.gs.service;

import com.gs.bean.Salary;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface SalaryService extends BaseService<String, Salary>{

    public Salary queryByUserId(String userId);
}