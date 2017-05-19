package com.gs.service.impl;

import com.gs.dao.VilidateDAO;
import com.gs.service.VilidateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Xiao-Qiang on 2017/5/18.
 */
@Service
public class VilidateServiceImpl implements VilidateService {

    @Resource
    private VilidateDAO vilidateDAO;

    @Override
    public int queryDataIsExistCompanyName(String companyName) {
        return vilidateDAO.queryDataIsExistCompanyName(companyName);
    }

    @Override
    public int queryDataIsExistCompanyTel(String companyTel) {
        return vilidateDAO.queryDataIsExistCompanyTel(companyTel);
    }

    @Override
    public int queryDataIsExistCompanyWebsite(String companyWebsite) {
        return vilidateDAO.queryDataIsExistCompanyWebsite(companyWebsite);
    }

    @Override
    public int queryDataIsExistUserEmail(String userEmail) {
        return vilidateDAO.queryDataIsExistUserEmail(userEmail);
    }

    @Override
    public int queryDataIsExistUserPhone(String userPhone) {
        return vilidateDAO.queryDataIsExistUserPhone(userPhone);
    }

    @Override
    public int queryDataIsExistRoleName(String roleName) {
        return vilidateDAO.queryDataIsExistRoleName(roleName);
    }

    @Override
    public int queryDataIsExistRoleDes(String roleDes) {
        return vilidateDAO.queryDataIsExistRoleDes(roleDes);
    }

    @Override
    public int queryDataIsExistModuleName(String moduleName) {
        return vilidateDAO.queryDataIsExistModuleName(moduleName);
    }

    @Override
    public int queryDataIsExistPermissionName(String permissionName) {
        return vilidateDAO.queryDataIsExistPermissionName(permissionName);
    }

    @Override
    public int queryDataIsExistPermissionZHName(String permissionZHName) {
        return vilidateDAO.queryDataIsExistPermissionZHName(permissionZHName);
    }

    @Override
    public int queryDataIsExistBrandName(String brandName) {
        return vilidateDAO.queryDataIsExistBrandName(brandName);
    }

    @Override
    public int queryDataIsExistModelName(String modelName) {
        return vilidateDAO.queryDataIsExistModelName(modelName);
    }

    @Override
    public int queryDataIsExistColorName(String colorName) {
        return vilidateDAO.queryDataIsExistColorName(colorName);
    }

    @Override
    public int queryDataIsExistPlateName(String plateName) {
        return vilidateDAO.queryDataIsExistPlateName(plateName);
    }

    @Override
    public int queryDataIsExistFixName(String maintainName) {
        return vilidateDAO.queryDataIsExistFixName(maintainName);
    }

    @Override
    public int queryDataIsExistSupplyTypeName(String supplyTypeName) {
        return vilidateDAO.queryDataIsExistSupplyTypeName(supplyTypeName);
    }

    @Override
    public int queryDataIsExistSupplyName(String supplyName) {
        return vilidateDAO.queryDataIsExistSupplyName(supplyName);
    }

    @Override
    public int queryDataIsExistAccTypeName(String accTypeName) {
        return vilidateDAO.queryDataIsExistAccTypeName(accTypeName);
    }

    @Override
    public int queryDataIsExistAccName(String accName) {
        return vilidateDAO.queryDataIsExistAccName(accName);
    }

    @Override
    public int queryDataIsExistInTypeName(String inTypeName) {
        return vilidateDAO.queryDataIsExistInTypeName(inTypeName);
    }

    @Override
    public int queryDataIsExistOuTypeName(String ouTypeName) {
        return vilidateDAO.queryDataIsExistOuTypeName(ouTypeName);
    }
}
