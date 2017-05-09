package com.gs.dao;

import com.gs.bean.MaintainDetail;
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
public interface MaintainDetailDAO extends BaseDAO<String, MaintainDetail>{

    /**
     * 根据维修保养记录ID计数
     * @param recordId
     * @return
     */
    public int countByRecordId(String recordId);

    /**
     * 根据维修保养记录ID分页查询
     * @param pager
     * @param recordId
     * @return
     */
    public List<MaintainDetail> queryPagerByRecordId(@Param("pager") Pager pager, @Param("recordId") String recordId);

    /**
     * 根据记录id和项目id判断该记录是否已经存在该项目，返回1表示存在，返回0表示不存在
     * @param recordId 维修保养记录的id
     * @param maintainId 维修保养项目的id
     * @return
     */
    public int queryIsDetail(@Param("recordId") String recordId, @Param("maintainId") String maintainId);

}