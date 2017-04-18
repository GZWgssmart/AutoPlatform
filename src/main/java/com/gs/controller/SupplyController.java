package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Supply;
import com.gs.common.bean.ControllerResult;
import com.gs.common.util.DateUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.SupplyService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * Created by Asa on 2017/4/17.
 */
@Controller
@RequestMapping("supply")
public class SupplyController {
        private Logger logger = (Logger) LoggerFactory.getLogger(SupplyTypeController.class);

        @Resource
        private SupplyService supplyService;

        @RequestMapping("/info")
        public String supplierInfo() {
            logger.info("进入供应商信息页");
            return "supply/supply_info";
        }

        @RequestMapping("add")
        public ControllerResult add() {
            logger.info("添加供应商");
            Supply supply = new Supply();
            supply.setSupplyId(UUIDUtil.uuid());
            supply.setSupplyName("123");
            supply.setSupplyTel("123456");
            supply.setSupplyPricipal("沙沙");
            supply.setSupplyAddress("江西赣州");
            supply.setSupplyBank("中国建设银行");
            supply.setSupplyBankAccount("Asa");
            supply.setSupplyBankNo("6217001288888888888");
            supply.setSupplyAlipay("123456789");
            supply.setSupplyWechat("123455");
            supply.setSupplyCreatedTime(Calendar.getInstance().getTime());
            supply.setSupplyTypeId(UUIDUtil.uuid());
            supply.setCompanyId(UUIDUtil.uuid());
            supply.setSupplyStatus("Y");
            supplyService.insert(supply);
            return ControllerResult.getSuccessResult("添加供应商成功");
        }




}
