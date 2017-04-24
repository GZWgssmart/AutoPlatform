package com.gs.dao;

import com.gs.bean.MaintainFix;
import com.gs.common.bean.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface MaintainFixDAO extends BaseDAO<String, MaintainFix>{
    public List<MaintainFix> queryBymaintainPager(Pager pager);
}