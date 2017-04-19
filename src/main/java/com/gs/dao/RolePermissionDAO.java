package com.gs.dao;

import java.util.List;

import com.gs.bean.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 根据角色名称获取权限字符串
 *
 */
@Repository
public interface RolePermissionDAO {
	
	public List<String> queryAllPermissionByRoleName(String roleName);

	public List<String> queryByRoleIdOrMeduleId(@Param("roleId") String roleId, @Param("moduleId") String moduleId);

	public void delByRoleIdAndPermissionId(@Param("permissionIds") String[] permissionIds, @Param("roleId") String roleId);

	public void addByRoleIdAndPermissionId(List<RolePermission> rps);
}
