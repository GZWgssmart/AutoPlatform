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

    public List<IncomingOutgoing> queryByInOutType(@Param("pager")Pager pager,@Param("incomingOutgoing")IncomingOutgoing incomingOutgoing);

    public int countByInOutType(IncomingOutgoing incomingOutgoing);

}