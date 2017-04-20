package com.gs.dao;

import com.gs.bean.Permission;
import com.gs.common.bean.Pager;
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
public interface PermissionDAO extends BaseDAO<String, Permission> {
    public List<Permission> queryByModuleId(String moduleId);

    public List<Permission> queryByModulePager(@Param("moduleId")String moduleId, @Param("pager")Pager pager);

    public List<Permission> queryByStatusPager(@Param("status")String status, @Param("pager")Pager pager);

    public int countModule(String moduleId);

    public int countStatus(String status);
}