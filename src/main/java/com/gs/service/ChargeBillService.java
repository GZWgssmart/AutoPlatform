package com.gs.service;

import com.gs.bean.ChargeBill;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface ChargeBillService extends BaseService<String, ChargeBill>{
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
    public List<ChargeBill> queryPagerByStatus(Pager pager, String status);

    /**
     * 根据查询条件计数
     * @param chargeBill
     * @return
     */
    public int countByCondition(ChargeBill chargeBill);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param chargeBill
     * @return
     */
    public List<ChargeBill> queryPagerByCondition(Pager pager, ChargeBill chargeBill);

}