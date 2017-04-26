package com.gs.service;

import com.gs.bean.IncomingOutgoing;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface IncomingOutgoingService extends BaseService<String, IncomingOutgoing>{

    public List<IncomingOutgoing> queryByInOutType(Pager pager, IncomingOutgoing incomingOutgoing);

    public int countByInOutType(IncomingOutgoing incomingOutgoing);

    public List<IncomingOutgoing> queryByDefault(int inOutType);

    public List<IncomingOutgoing> queryByCondition(String startTime,String endTime,int inOutType,String type);
}