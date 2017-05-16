package com.gs.dao;

import com.gs.bean.info.MaterialReturnInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Xiao-Qiang on 2017/5/16.
 */
@Repository
public interface MaterialReturnInfoDAO {

    /**添加退料信息*/
    public void insertReturn(MaterialReturnInfo materialReturnInfo);

    /**通过维修记录编号查询是否存在有这条记录*/
    public int isRecordExist(@Param("recordId") String recordId, @Param("accId") String accId);
}
