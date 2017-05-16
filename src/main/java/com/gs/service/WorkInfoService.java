package com.gs.service;

import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface WorkInfoService extends BaseService<String, WorkInfo>{

    // 技师查询自己的工单
    public List<WorkInfo> queryWorkUserId(@Param("pager")Pager pager, @Param("userId") String userId);

    // 技师查询自己的工单个数
    public int countWorkUserId(String userId);

    /*
   * 默认查询本月的工单统计
   * */
    public List<WorkInfo> queryByDefault(String maintainOrFix, String companyId);

    /*
    * 根据年，月，季度，周，日查询所有工单
    * */
    public List<WorkInfo> queryByCondition(String startTime, String endTime, String maintainOrFix,
                                           String type, String companyId);
}