package com.gs.service;

import com.gs.bean.OutgoingType;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface OutgoingTypeService extends BaseService<String, OutgoingType>{

    public OutgoingType queryByName(String outTypeName);
}