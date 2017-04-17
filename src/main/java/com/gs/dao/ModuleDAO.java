package com.gs.dao;

import com.gs.bean.Module;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:35:15
 */
@Repository
public interface ModuleDAO extends BaseDAO<String, Module> {

    public List<Module> queryByStatusPager(@Param("moduleStatus") String moduleStatus, @Param("pager") Pager pager);
    public int countByStatus(String moduleStatus);
}