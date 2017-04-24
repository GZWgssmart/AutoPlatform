package com.gs.dao;

import com.gs.bean.MessageSend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface MessageSendDAO extends BaseDAO<String, MessageSend>{


    public void batchUpdateBySendMsg(@Param("idList") String[] idList, @Param("sendMsg")String sendMsg);
}