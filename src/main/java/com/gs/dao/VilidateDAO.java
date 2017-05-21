package com.gs.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by Xiao-Qiang on 2017/5/18.
 * 数据唯一验证专用DAO
 */
@Repository
public interface VilidateDAO {

    /*公司表名称验证唯一**/
    public int queryDataIsExistCompanyName(String companyName);

    /*公司表电话验证唯一**/
    public int queryDataIsExistCompanyTel(String companyTel);

    /*公司表官网验证唯一**/
    public int queryDataIsExistCompanyWebsite(String companyWebsite);

    /**用户表邮箱验证唯一*/
    public int queryDataIsExistUserEmail(String userEmail);

    /**用户表手机验证唯一*/
    public int queryDataIsExistUserPhone(String userPhone);

    /**用户表身份证验证唯一*/
    public int queryDataIsExistUserIdentity(String userIdentity);

    /**角色表英文名称验证唯一*/
    public int queryDataIsExistRoleName(String roleName);

    /**角色表中文名称验证唯一*/
    public int queryDataIsExistRoleDes(String roleDes);

    /**模块表名称验证唯一*/
    public int queryDataIsExistModuleName(String moduleName);

    /**权限表名称验证唯一*/
    public int queryDataIsExistPermissionName(String permissionName);

    /**权限表中文名称验证唯一*/
    public int queryDataIsExistPermissionZHName(String permissionZHName);

    /**品牌表名称验证唯一*/
    public int queryDataIsExistBrandName(String brandName);

    /**车型表名称验证唯一*/
    public int queryDataIsExistModelName(String modelName);

    /**顔色表名称验证唯一*/
    public int queryDataIsExistColorName(String colorName);

    /**车牌表名称验证唯一*/
    public int queryDataIsExistPlateName(String plateName);

    /**项目表名称验证唯一*/
    public int queryDataIsExistFixName(String maintainName);

    /**供应商分类表名称验证唯一*/
    public int queryDataIsExistSupplyTypeName(String supplyTypeName);

    /**供应商表名称验证唯一*/
    public int queryDataIsExistSupplyName(String supplyName);

    /**配件分类表名称验证唯一*/
    public int queryDataIsExistAccTypeName(String accTypeName);

    /**配件表名称验证唯一*/
    public int queryDataIsExistAccName(String accName);

    /**收入类型表名称验证唯一*/
    public int queryDataIsExistInTypeName(String inTypeName);

    /**支出类型表名称验证唯一*/
    public int queryDataIsExistOuTypeName(String ouTypeName);
}
