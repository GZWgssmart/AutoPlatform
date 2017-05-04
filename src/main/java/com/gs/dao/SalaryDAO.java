package com.gs.dao;

import com.gs.bean.Salary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface SalaryDAO extends BaseDAO<String, Salary>{

    public Salary queryByUserId(String userId);

    public void addInsert(List<Salary> salarys);
}