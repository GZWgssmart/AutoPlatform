package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.CarColor;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.CarColorService;
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
 * Created by root on 2017/4/16.
 */
@Controller
@RequestMapping("/carColor")
public class CarColorController {

    @Resource
    private CarColorService carColorService;
    private Logger logger = (Logger) LoggerFactory.getLogger(CarColorController.class);

    @ResponseBody
    @RequestMapping(value = "insertCarColor", method = RequestMethod.POST)
    public ControllerResult insertCarColor(CarColor carColor){
        carColor.setColorStatus("Y");
        System.out.println(carColor);
        logger.info("添加汽车颜色成功");
        carColorService.insert(carColor);
        return ControllerResult.getSuccessResult("添加汽车颜色成功");
    }

    @ResponseBody
    @RequestMapping(value = "uploadCarColor",method = RequestMethod.POST)
    public ControllerResult uploadCarColor(CarColor carColor){
        carColorService.update(carColor);
            logger.info("更新汽车颜色成功");
            return ControllerResult.getSuccessResult("更新汽车颜色成功");
    }

    @ResponseBody
    @RequestMapping(value = "StatusInactive" , method = RequestMethod.GET)
    public ControllerResult statusInactive(String Id){
        if(Id!=null){
            return ControllerResult.getSuccessResult(",.,.,.,.");
        }
        return ControllerResult.getFailResult("///////////////////");
    }

    @ResponseBody
    @RequestMapping(value = "queryByPager" , method = RequestMethod.GET)
    public Pager4EasyUI<CarColor> queryByPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        Pager pager = new Pager();
        logger.info("分页查询所有颜色");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carColorService.count());
        List<CarColor>carColorList = carColorService.queryByPager(pager);
        return new Pager4EasyUI<CarColor>(pager.getTotalRecords(),carColorList);
    }

    @ResponseBody
    @RequestMapping(value = "car_color_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCarColorAll() {
        logger.info("查询汽车颜色");
        List<CarColor> carColors = carColorService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (CarColor carColor : carColors) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(carColor.getColorId());
            comboBox4EasyUI.setText(carColor.getColorName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }
}
