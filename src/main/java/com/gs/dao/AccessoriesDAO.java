package com.gs.dao;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesType;
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
public interface AccessoriesDAO extends BaseDAO<String, Accessories>{

    public List<Accessories> queryByStatusPager(@Param("accStatus") String accStatus, @Param("pager") Pager pager);
    public int countByStatus(String status);
    public List<Accessories> queryByIdPager(@Param("id") String id, @Param("pager") Pager pager);
}