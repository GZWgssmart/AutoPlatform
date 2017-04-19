package com.gs.dao;

import com.gs.bean.Permission;
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
}