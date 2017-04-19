package com.gs.service;

import java.util.Collection;
import java.util.List;

import com.gs.bean.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.Permission;

/**
 * 根据角色名称获取权限字符串
 *
 */
public interface RolePermissionService {
	
	public Collection<Permission> queryAllPermissionByRoleName(String roleName);

	public List<String> queryByRoleIdOrMeduleId(String roleId, String moduleId);

	public void delByRoleIdAndPermissionId(String[] permissionIds, String roleId);

	public void addByRoleIdAndPermissionId(List<RolePermission> rps);
}
