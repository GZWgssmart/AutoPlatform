package com.gs.service;

import com.gs.bean.AccessoriesType;
import com.gs.bean.Module;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface AccessoriesTypeService extends BaseService<String, AccessoriesType>{

    public List<AccessoriesType> queryByStatusPager(String accTypeStatus, Pager pager);
    public int countByStatus(String accTypeStatus);

}