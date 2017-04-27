package com.gs.dao;

import com.gs.bean.AccessoriesType;
import com.gs.bean.Module;
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
public interface AccessoriesTypeDAO extends BaseDAO<String, AccessoriesType>{

    public List<AccessoriesType> queryByStatusPager(@Param("accTypeStatus") String accTypeStatus, @Param("pager") Pager pager);
    public int countByStatus(String status);

}