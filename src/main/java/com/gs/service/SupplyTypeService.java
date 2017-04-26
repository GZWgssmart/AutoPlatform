package com.gs.service;

import com.gs.bean.SupplyType;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface SupplyTypeService extends BaseService<String, SupplyType>{

    /**
     * 根据状态计数
     * @param status
     * @return
     */
    public int countByStatus(String status);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
    public List<SupplyType> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status);



}