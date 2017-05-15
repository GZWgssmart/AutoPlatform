package com.gs.dao;


import com.gs.bean.info.MaterialUseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/5/15.
 */
@Repository
public interface MaterialUseInfoDAO {

    /**添加这条维修保养记录下的所有物料申请*/
    public void addByRecordIdMu(List<MaterialUseInfo> muis);
}
