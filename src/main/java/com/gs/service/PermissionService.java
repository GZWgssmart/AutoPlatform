package com.gs.service;

import com.gs.bean.Permission;
import com.gs.common.bean.Pager;

import java.util.List;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:36:52
 */
public interface PermissionService extends BaseService<String, Permission> {
    public List<Permission> queryByModuleId(String moduleId);

    public List<Permission> queryByModulePager(String moduleId, Pager pager);

    public int countModule(String moduleId);
}