package com.gs.dao;

import com.gs.bean.Complaint;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface ComplaintDAO extends BaseDAO<String, Complaint>{

    /**
     * 回复更新
     *
     */
    public void updateReply(Complaint complaint);




}