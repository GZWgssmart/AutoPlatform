package com.gs.service;

import com.gs.bean.MaterialListInfo;
import com.gs.common.bean.Pager;

import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/4/26.
 */
public interface MaterialListInfoService extends BaseService<String, MaterialListInfo> {

    public List<MaterialListInfo> queryByStatus(Pager pager, String status);
    public int termCount(String userName, String startTime, String endTime);
    public List<MaterialListInfo> termQueryPager(Pager pager, String userName, String startTime, String endTime);
}
