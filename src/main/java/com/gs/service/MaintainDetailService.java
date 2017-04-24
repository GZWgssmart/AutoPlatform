package com.gs.service;

import com.gs.bean.MaintainDetail;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MaintainDetailService extends BaseService<String, MaintainDetail>{
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

}