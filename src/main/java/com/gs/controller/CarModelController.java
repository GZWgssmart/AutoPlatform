package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.CarModel;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.CarModelService;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("/carModel")
public class CarModelController {
    private Logger logger = (Logger) LoggerFactory.getLogger(CarModelController.class);
    @Resource
    private CarModelService carModelService;

    @ResponseBody
    @RequestMapping(value = "car_model_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCarModelAll() {
        logger.info("查询汽车车型");
        List<CarModel> CarModels = carModelService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (CarModel carModel : CarModels) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(carModel.getModelId());
            comboBox4EasyUI.setText(carModel.getModelName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

    @ResponseBody
    @RequestMapping(value = "insertCarModel", method = RequestMethod.POST)
    public ControllerResult insertCarModel(CarModel carmodel) {
        logger.info("添加汽车车型成功");
        System.out.println(carmodel);
        carModelService.insert(carmodel);
        return ControllerResult.getSuccessResult("添加汽车车型成功");
    }

    @ResponseBody
    @RequestMapping(value = "uploadCarModel", method = RequestMethod.POST)
    public ControllerResult uploadCarModel(CarModel carmodel) {
        carModelService.update(carmodel);
        logger.info("更新汽车车型成功");
        return ControllerResult.getSuccessResult("更新汽车车型成功");
    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarModel> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        Pager pager = new Pager();
        logger.info("分页查询所有车型");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carModelService.count());
        List<CarModel> carModelList = carModelService.queryByPager(pager);
        for(CarModel model :carModelList){
            System.out.println(model.getBrand().getBrandName());
        }
        return new Pager4EasyUI<CarModel>(pager.getTotalRecords(), carModelList);
    }
}
