package com.gs.service;

import com.gs.bean.IncomingType;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface IncomingTypeService extends BaseService<String, IncomingType>{

    public List<IncomingType> queryPagerStatus(String status, Pager pager);
    public int countStatus(String status);
}