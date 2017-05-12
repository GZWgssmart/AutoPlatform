package com.gs.service;

import com.gs.bean.Complaint;
import com.gs.bean.User;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface ComplaintService extends BaseService<String, Complaint>{

    public void updateReply(Complaint complaint);
}