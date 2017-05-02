package com.gs.service;

import com.gs.bean.Supply;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface SupplyService extends BaseService<String, Supply>{

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
    public List<Supply> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status);

    /**
     * 根据查询条件计数
     * @param supply
     * @return
     */
    public int countByCondition(Supply supply);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param supply
     * @return
     */
    public List<Supply> queryPagerByCondition(@Param("pager") Pager pager, @Param("supply") Supply supply);

}