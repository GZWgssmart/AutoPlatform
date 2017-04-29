package com.gs.service;

import com.gs.bean.MaintainFixAcc;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MaintainFixAccService extends BaseService<String, MaintainFixAcc>{
    public List<MaintainFixAcc> queryAllByMaintainId(String[] ids);
}