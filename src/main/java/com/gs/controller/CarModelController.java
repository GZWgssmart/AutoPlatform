package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.CarModel;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.SessionGetUtil;
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
    public List<ComboBox4EasyUI> queryCarModelAll(String brandId) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        logger.info("查询汽车车型");
        List<CarModel> CarModels = carModelService.queryByBrandId(brandId);
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
    @RequestMapping(value = "carModel_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCarModelAlls() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        logger.info("查询全部汽车车型");
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
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            logger.info("添加汽车车型成功");
            System.out.println(carmodel);
            carModelService.insert(carmodel);
            return ControllerResult.getSuccessResult("添加汽车车型成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }

    }

    @ResponseBody
    @RequestMapping(value = "uploadCarModel", method = RequestMethod.POST)
    public ControllerResult uploadCarModel(CarModel carmodel) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            carModelService.update(carmodel);
            logger.info("更新汽车车型");
            return ControllerResult.getSuccessResult("更新汽车车型成功");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarModel> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        Pager pager = new Pager();
        logger.info("分页查询所有车型");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carModelService.count());
        List<CarModel> carModelList = carModelService.queryByPager(pager);
        return new Pager4EasyUI<CarModel>(pager.getTotalRecords(), carModelList);
    }

    @ResponseBody
    @RequestMapping(value = "searchPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarModel> search(@Param("brandId") String brandId, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        Pager pager = new Pager();
        logger.info("模糊查询所有车型");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carModelService.searchCount(brandId));
        List<CarModel> carModelList = carModelService.searchByPager(brandId, pager);
        return new Pager4EasyUI<CarModel>(pager.getTotalRecords(), carModelList);
    }

    @ResponseBody
    @RequestMapping(value = "modelStatusModify", method = RequestMethod.GET)
    public ControllerResult modelStatusModify(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (status.equals("Y")) {
                logger.info("冻结汽车车型");
                carModelService.inactive(id);
            } else if (status.equals("N")) {
                logger.info("激活汽车车型");
                carModelService.active(id);
            }
            return ControllerResult.getSuccessResult("操作成功");
        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryByModelPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarModel> queryByModelPager(@Param("status") String status, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carModelService.statusCount(status));
        List<CarModel> carModelList = carModelService.queryByModelStatusPager(status, pager);
        return new Pager4EasyUI<CarModel>(pager.getTotalRecords(), carModelList);
    }

}
