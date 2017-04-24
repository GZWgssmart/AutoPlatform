package com.gs.service;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesType;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface AccessoriesService extends BaseService<String, Accessories>{

    public List<Accessories> queryByStatusPager(String accStatus, Pager pager);
    public int countByStatus(String accStatus);


}