package com.gs.service;

import com.gs.bean.MessageSend;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MessageSendService extends BaseService<String, MessageSend>{

    public void batchUpdateBySendMsg( String[] idList, String sendMsg);

}