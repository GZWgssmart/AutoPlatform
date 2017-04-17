package com.gs.service;

import com.gs.bean.Module;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:36:52
 */
public interface ModuleService extends BaseService<String, Module> {

    public List<Module> queryByStatusPager(String moduleStatus, Pager pager);
    public int countByStatus(String moduleStatus);
}