package com.gs.dao;

import com.gs.bean.User;
import com.gs.bean.WorkInfo;
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
public interface WorkInfoDAO extends BaseDAO<String, WorkInfo>{

    // 技师查询自己的工单
    public List<WorkInfo> queryWorkUserId(@Param("pager")Pager pager, @Param("userId") String userId);

    // 技师查询自己的工单个数
    public int countWorkUserId(String userId);

    /*
    * 默认查询本月的工单统计
    * */
    public List<WorkInfo> queryByDefault(@Param("maintainOrFix") String maintainOrFix, @Param("companyId")String companyId);

    /*
    * 根据年，月，季度，周，日查询所有工单
    * */
    public List<WorkInfo> queryByCondition(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("maintainOrFix")String maintainOrFix,
                                                   @Param("type")String type, @Param("companyId")String companyId);
}