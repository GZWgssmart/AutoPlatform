package com.gs.service;

import com.gs.bean.MaintainRemind;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MaintainRemindService extends BaseService<String, MaintainRemind>{
    /**
     * 根据查询条件计数
     * @param remind
     * @return
     */
    public int countByCondition(MaintainRemind remind);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param remind
     * @return
     */
    public List<MaintainRemind> queryPagerByCondition(Pager pager, MaintainRemind remind);
}