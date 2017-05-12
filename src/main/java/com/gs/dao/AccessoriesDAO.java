package com.gs.dao;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesType;
import com.gs.bean.User;
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

    public List<Accessories> queryByStatusPager(@Param("accStatus") String accStatus, @Param("pager") Pager pager, @Param("user") User user);
    public int countByStatus(@Param("status") String status, @Param("user") User user);

    public List<Accessories> queryByIdPager(@Param("id") String id, @Param("pager") Pager pager, @Param("user") User user);
    public int countByTypeId(String accTypeId, User user);

    public int countByCondition(@Param("accessories") Accessories accessories, @Param("user") User user);
    public List<Accessories> queryByCondition(@Param("pager") Pager pager, @Param("accessories") Accessories accessories, @Param("user") User user);

    public void updateIdle(@Param("id") String id,  @Param("lastCount") int  lastCount, @Param("user") User user);

}