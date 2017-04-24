package com.gs.service;

import com.gs.bean.MaintainFix;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MaintainFixService extends BaseService<String, MaintainFix>{
    public List<MaintainFix> queryBymaintainPager(Pager pager);
}