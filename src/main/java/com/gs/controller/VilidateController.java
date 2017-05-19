package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.service.VilidateService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xiao-Qiang on 2017/5/19.
 */
@Controller
@RequestMapping("vilidate")
public class VilidateController {

    private Logger logger = (Logger) LoggerFactory.getLogger(VilidateController.class);

    @Resource
    private VilidateService vilidateService;

    @ResponseBody
    @RequestMapping(value = "queryIsExist_userPhone", method = RequestMethod.GET)
    public String queryIsExistUserPhone(@Param("userPhone")String userPhone, @Param("editPhone") String editPhone) {
        try {
            logger.info("手机号验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editPhone.equals(userPhone)) {
                int isExist = vilidateService.queryDataIsExistUserPhone(userPhone);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("手机号验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_userEmail", method = RequestMethod.GET)
    public String queryIsExistUserEmail(@Param("userEmail")String userEmail, @Param("editEmail") String editEmail) {
        try {
            logger.info("邮箱验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editEmail.equals(userEmail)) {
                int isExist = vilidateService.queryDataIsExistUserEmail(userEmail);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("邮箱验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_roleName", method = RequestMethod.GET)
    public String queryIsExistRoleName(String roleName) {
        try {
            logger.info("角色英文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            int isExist = vilidateService.queryDataIsExistRoleName(roleName);
            if (isExist > 0) {
                result = false;
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("角色英文名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_roleDes", method = RequestMethod.GET)
    public String queryIsExistRoleDes(@Param("roleDes") String roleDes, @Param("editDes") String editDes) {
        try {
            logger.info("角色中文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editDes.equals(roleDes)) {
                int isExist = vilidateService.queryDataIsExistRoleDes(roleDes);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("角色中文名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_moduleName", method = RequestMethod.GET)
    public String queryIsExistModuleName(@Param("moduleName") String moduleName, @Param("editName") String editName) {
        try {
            logger.info("模块名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editName.equals(moduleName)) {
                int isExist = vilidateService.queryDataIsExistModuleName(moduleName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("模块名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_PZHN", method = RequestMethod.GET)
    public String queryIsExistPermissionZHName(@Param("permissionZHName") String permissionZHName, @Param("editZhName") String editZhName) {
        try {
            logger.info("权限中文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editZhName.equals(permissionZHName)) {
                int isExist = vilidateService.queryDataIsExistPermissionZHName(permissionZHName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("权限中文名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_PN", method = RequestMethod.GET)
    public String queryIsExistPermissionName(@Param("permissionName") String permissionName, @Param("editPName") String editPName) {
        try {
            logger.info("权限名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editPName.equals(permissionName)) {
                int isExist = vilidateService.queryDataIsExistPermissionName(permissionName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("权限名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_supplyName", method = RequestMethod.GET)
    public String queryIsExistSupplyName(@Param("supplyName") String supplyName, @Param("editSupplyName") String editSupplyName) {
        try {
            logger.info("供应商中文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editSupplyName.equals(supplyName)) {
                int isExist = vilidateService.queryDataIsExistSupplyName(supplyName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("供应商中文名称验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIsExist_supplyTypeName", method = RequestMethod.GET)
    public String queryIsExistSupplyTypeName(@Param("supplyTypeName") String supplyTypeName, @Param("editSupplyTypeName") String editSupplyTypeName) {
        try {
            logger.info("供应商分类中文名称验证");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editSupplyTypeName.equals(supplyTypeName)) {
                int isExist = vilidateService.queryDataIsExistSupplyTypeName(supplyTypeName);
                if (isExist > 0) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("供应商分类中文名称验证失败，出现了异常");
            return null;
        }
    }

}
