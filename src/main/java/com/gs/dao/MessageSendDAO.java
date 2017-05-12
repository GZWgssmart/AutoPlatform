package com.gs.dao;

import com.gs.bean.MessageSend;
import com.gs.bean.User;
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
public interface MessageSendDAO extends BaseDAO<String, MessageSend>{

    /**
     * 根据查询条件计数
     * @param idList
     * @param sendMsg
     * @param user
     * @return
     */
    public void batchUpdateBySendMsg(@Param("idList") String[] idList,
                                     @Param("sendMsg")String sendMsg,@Param("user")User user);
    /**
     * 根据查询条件计数
     * @param msd
     * @param user
     * @return
     */
    public void addMessageId(@Param("msd")List<MessageSend> msd,@Param("user")User user);
}