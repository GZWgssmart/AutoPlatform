package com.gs.dao;

import com.gs.bean.Salary;
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
public interface SalaryDAO extends BaseDAO<String, Salary>{

    public List<Salary> queryByUserId(@Param("pager")Pager pager,@Param("userId") String userId);
    public int countByUserId(String userId);

    public void addInsert(List<Salary> salarys);
}