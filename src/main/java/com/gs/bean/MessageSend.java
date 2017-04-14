package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class MessageSend {
	private String messageId;
	private String userId;
	private String sendMsg;
	private Date sendTime;
	private Date sendCreatedTime;

	public String getMessageId(){
		return this.messageId;
	}
	public void setMessageId(String messageId){
		this.messageId=messageId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getSendMsg(){
		return this.sendMsg;
	}
	public void setSendMsg(String sendMsg){
		this.sendMsg=sendMsg;
	}

	public Date getSendTime(){
		return this.sendTime;
	}
	public void setSendTime(Date sendTime){
		this.sendTime=sendTime;
	}

	public Date getSendCreatedTime(){
		return this.sendCreatedTime;
	}
	public void setSendCreatedTime(Date sendCreatedTime){
		this.sendCreatedTime=sendCreatedTime;
	}

}