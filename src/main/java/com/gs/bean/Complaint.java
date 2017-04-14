package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class Complaint{
	private String complaintId;
	private String userId;
	private String complaintContent;
	private Date complaintCreatedTime;
	private String complaintReply;
	private Date complaintReplyTime;
	private String complaintReplyUser;

	public String getComplaintId(){
		return this.complaintId;
	}
	public void setComplaintId(String complaintId){
		this.complaintId=complaintId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getComplaintContent(){
		return this.complaintContent;
	}
	public void setComplaintContent(String complaintContent){
		this.complaintContent=complaintContent;
	}

	public Date getComplaintCreatedTime(){
		return this.complaintCreatedTime;
	}
	public void setComplaintCreatedTime(Date complaintCreatedTime){
		this.complaintCreatedTime=complaintCreatedTime;
	}

	public String getComplaintReply(){
		return this.complaintReply;
	}
	public void setComplaintReply(String complaintReply){
		this.complaintReply=complaintReply;
	}

	public Date getComplaintReplyTime(){
		return this.complaintReplyTime;
	}
	public void setComplaintReplyTime(Date complaintReplyTime){
		this.complaintReplyTime=complaintReplyTime;
	}

	public String getComplaintReplyUser(){
		return this.complaintReplyUser;
	}
	public void setComplaintReplyUser(String complaintReplyUser){
		this.complaintReplyUser=complaintReplyUser;
	}

}