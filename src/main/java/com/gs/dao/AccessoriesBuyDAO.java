package com.gs.dao;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesBuy;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:35:15
 */
@Repository
public interface AccessoriesBuyDAO extends BaseDAO<String, AccessoriesBuy> {
    public int batchDeleteAcc(@Param("ids") String[] ids);

    public List<Accessories> queryByCheckStates(@Param("checkState") String checkState);

    public int countByBuyState(@Param("buyState") String buyState);

    public int countByCheckState(@Param("checkState") String checkState);

    public List<AccessoriesBuy> queryByBuyStatePager(Pager pager);

    public List<AccessoriesBuy> queryByCheckStatePager(Pager pager);

    public int countByAccName(@Param("accName") String accName);

    public List<AccessoriesBuy> queryByAccNamePager(@Param("pager") Pager pager, @Param("accName") String accName);

    // 在选择购买时间范围内根据名称来查找
    public List<AccessoriesBuy> queryByBuyTimeScopeByAccNamePager(@Param("pager") Pager pager, @Param("accName") String accName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd);

    public int countByBuyTimeScope(@Param("accName") String accName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd);


    /*
    * 默认查询本月的下单统计
    * */
    public List<AccessoriesBuy> queryByDefaultCount(@Param("companyId")String companyId);

    /*
    * 根据年，月，季度，周，日查询所有下单统计
    * */
    public List<AccessoriesBuy> queryByConditionCount(@Param("startTime")String startTime,@Param("endTime")String endTime,
                                                   @Param("type")String type, @Param("companyId")String companyId);

    /*
   * 默认查询本月的支付统计
   * */
    public List<AccessoriesBuy> queryByDefaultPay(@Param("companyId")String companyId);

    /*
    * 根据年，月，季度，周，日查询所有支付统计
    * */
    public List<AccessoriesBuy> queryByConditionPay(@Param("startTime")String startTime,@Param("endTime")String endTime,
                                                      @Param("type")String type, @Param("companyId")String companyId);
}