package com.gs.dao;

import com.gs.bean.IncomingOutgoing;
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
public interface IncomingOutgoingDAO extends BaseDAO<String, IncomingOutgoing>{

    /*
    *  查询支出和收入
    * */
    public List<IncomingOutgoing> queryByInOutType(@Param("pager")Pager pager,@Param("incomingOutgoing")IncomingOutgoing incomingOutgoing);

    public int countByInOutType(IncomingOutgoing incomingOutgoing);

    /*
    * 默认查询本月的财务统计
    * */
    public List<IncomingOutgoing> queryByDefault(@Param("inOutType") int inOutType);

    /*
    * 根据年，月，季度，周，日查询所有收支记录
    * */
    public List<IncomingOutgoing> queryByCondition(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("inOutType")int inOutType,@Param("type")String type);
}