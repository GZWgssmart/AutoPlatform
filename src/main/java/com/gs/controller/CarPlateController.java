package com.gs.controller;

import com.gs.bean.CarPlate;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.service.CarPlateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2017/4/17.
 */
@Controller
public class CarPlateController {
    private Logger logger = (Logger) LoggerFactory.getLogger(CarPlateController.class);

    @Resource
    private CarPlateService carPlateService;

    @ResponseBody
    @RequestMapping(value = "car_plate_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCarModelAll() {
        logger.info("查询汽车车型");
        List<CarPlate> CarModels = carPlateService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (CarPlate carPlatel : CarModels) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(carPlatel.getPlateId());
            comboBox4EasyUI.setText(carPlatel.getPlateName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }
}
