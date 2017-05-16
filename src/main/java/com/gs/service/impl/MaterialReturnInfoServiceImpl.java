package com.gs.service.impl;

import com.gs.bean.info.MaterialReturnInfo;
import com.gs.dao.MaterialReturnInfoDAO;
import com.gs.service.MaterialReturnInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Xiao-Qiang on 2017/5/16.
 */
@Service
public class MaterialReturnInfoServiceImpl implements MaterialReturnInfoService {

    @Resource
    private MaterialReturnInfoDAO mrid;

    public void insertReturn(MaterialReturnInfo materialReturnInfo) {
        mrid.insertReturn(materialReturnInfo);
    }

    public int isRecordExist(String recordId, String accId) {
        return mrid.isRecordExist(recordId, accId);
    }
}
