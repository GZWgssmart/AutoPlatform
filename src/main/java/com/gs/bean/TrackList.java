package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class TrackList {
	private String trackId;
	private String userId;
	private String trackContent;
	private int serviceEvaluate;
	private String trackUser;
	private Date trackCreatedTime;

	public String getTrackId(){
		return this.trackId;
	}
	public void setTrackId(String trackId){
		this.trackId=trackId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getTrackContent(){
		return this.trackContent;
	}
	public void setTrackContent(String trackContent){
		this.trackContent=trackContent;
	}

	public int getServiceEvaluate(){
		return this.serviceEvaluate;
	}
	public void setServiceEvaluate(int serviceEvaluate){
		this.serviceEvaluate=serviceEvaluate;
	}

	public String getTrackUser(){
		return this.trackUser;
	}
	public void setTrackUser(String trackUser){
		this.trackUser=trackUser;
	}

	public Date getTrackCreatedTime(){
		return this.trackCreatedTime;
	}
	public void setTrackCreatedTime(Date trackCreatedTime){
		this.trackCreatedTime=trackCreatedTime;
	}

}