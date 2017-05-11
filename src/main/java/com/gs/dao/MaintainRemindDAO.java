package com.gs.dao;

import com.gs.bean.MaintainRemind;
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
public interface MaintainRemindDAO extends BaseDAO<String, MaintainRemind>{

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
    public List<MaintainRemind> queryPagerByCondition(@Param("pager") Pager pager, @Param("remind") MaintainRemind remind);

}