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
    public int queryDateIsExistUserEmail(String userEmail) {
        return vilidateDAO.queryDateIsExistUserEmail(userEmail);
    }

    @Override
    public int queryDateIsExistUserPhone(String userPhone) {
        return vilidateDAO.queryDateIsExistUserPhone(userPhone);
    }

    @Override
    public int queryDateIsExistRoleName(String roleName) {
        return vilidateDAO.queryDateIsExistRoleName(roleName);
    }

    @Override
    public int queryDateIsExistRoleDes(String roleDes) {
        return vilidateDAO.queryDateIsExistRoleDes(roleDes);
    }

    @Override
    public int queryDateIsExistModuleName(String moduleName) {
        return vilidateDAO.queryDateIsExistModuleName(moduleName);
    }

    @Override
    public int queryDateIsExistPermissionName(String permissionName) {
        return vilidateDAO.queryDateIsExistPermissionName(permissionName);
    }

    @Override
    public int queryDateIsExistPermissionZHName(String permissionZHName) {
        return vilidateDAO.queryDateIsExistPermissionZHName(permissionZHName);
    }

    @Override
    public int queryDateIsExistBrandName(String brandName) {
        return vilidateDAO.queryDateIsExistBrandName(brandName);
    }

    @Override
    public int queryDateIsExistModelName(String modelName) {
        return vilidateDAO.queryDateIsExistModelName(modelName);
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
