package com.gs.dao;

import com.gs.bean.ChargeBill;
import com.gs.bean.Checkin;
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
public interface ChargeBillDAO extends BaseDAO<String, ChargeBill>{
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
    public List<ChargeBill> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status);

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
    public List<ChargeBill> queryPagerByCondition(@Param("pager") Pager pager, @Param("chargeBill") ChargeBill chargeBill);

}
