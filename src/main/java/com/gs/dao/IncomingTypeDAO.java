package com.gs.dao;

import com.gs.bean.IncomingType;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface IncomingTypeDAO extends BaseDAO<String, IncomingType>{

    public List<IncomingType> queryPagerStatus(@Param("status")String status,@Param("pager")Pager pager);
    public int countStatus(String status);

}