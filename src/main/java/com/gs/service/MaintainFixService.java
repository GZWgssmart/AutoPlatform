package com.gs.service;

import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MaintainFixService extends BaseService<String, MaintainFix>{
    public List<MaintainFix> queryBymaintainPager(@Param("pager") Pager pager, @Param("user") User user);
    public int MaintainCont(User user);

    /*
   * 查询项目是维修还是保养
   * */
    public List<MaintainFix> queryByType(User user,String maintainOrFix);
}