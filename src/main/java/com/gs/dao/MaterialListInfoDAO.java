package com.gs.dao;

import com.gs.bean.MaterialListInfo;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/4/26.
 */

@Repository
public interface MaterialListInfoDAO  extends BaseDAO<String, MaterialListInfo> {

    public List<MaterialListInfo> queryByStatus(@Param("pager") Pager pager, @Param("status") String status);
    public int termCount(@Param("userName") String userName, @Param("startTime") String startTime, @Param("endTime") String endTime);
    public List<MaterialListInfo> termQueryPager(@Param("pager") Pager pager,@Param("userName") String userName, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
