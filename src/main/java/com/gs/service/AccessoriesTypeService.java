package com.gs.service;

import com.gs.bean.AccessoriesType;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface AccessoriesTypeService extends BaseService<String, AccessoriesType>{

    public int countByStatus(String accTypeStatus);

}