package com.gs.service;

import com.gs.bean.info.MaterialReturnInfo;

/**
 * Created by Xiao-Qiang on 2017/5/16.
 */
public interface MaterialReturnInfoService {

    /**添加退料信息*/
    public void insertReturn(MaterialReturnInfo materialReturnInfo);

    /**通过维修记录编号查询是否存在有这条记录*/
    public int isRecordExist(String recordId, String accId);
}
