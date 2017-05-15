package com.gs.service;

import com.gs.bean.info.MaterialUseInfo;

import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/5/15.
 */
public interface MaterialUseInfoService {

    /**添加这条维修保养记录下的所有物料申请*/
    public void addByRecordIdMu(List<MaterialUseInfo> muis);
}
