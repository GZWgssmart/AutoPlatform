package com.gs.service;

import com.gs.bean.MaterialUse;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MaterialUseService extends BaseService<String, MaterialUse>{


    /*
    * 根据年，月，季度，周，日查询配件分类领料数量
    * */
    public List<MaterialUse> queryByConditionUse(String startTime, String endTime,
                                                 String type, String companyId,
                                                 String accTypeId);
}