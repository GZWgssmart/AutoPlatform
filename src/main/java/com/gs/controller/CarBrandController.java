package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.CarBrand;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.CarBrandService;
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
@RequestMapping("/carBrand")
public class CarBrandController {

    @Resource
    private CarBrandService carBrandService;
    private Logger logger = (Logger) LoggerFactory.getLogger(CarBrandController.class);

    @ResponseBody
    @RequestMapping(value = "insertCarBrand", method = RequestMethod.POST)
    public ControllerResult insertCarBrand(CarBrand carBrand) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            logger.info("添加汽车品牌");
            carBrandService.insert(carBrand);
            return ControllerResult.getSuccessResult("添加汽车品牌成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public Pager4EasyUI<CarBrand> searchByPager(@Param("brandName") String brandName, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carBrandService.searchCount(brandName));
        List<CarBrand> carBrandList = carBrandService.searchByPager(brandName, pager);
        return new Pager4EasyUI<CarBrand>(pager.getTotalRecords(), carBrandList);
    }

    @ResponseBody
    @RequestMapping(value = "uploadCarBrand", method = RequestMethod.POST)
    public ControllerResult uploadCarBrand(CarBrand carBrand) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            carBrandService.update(carBrand);
            logger.info("更新汽车品牌");
            return ControllerResult.getSuccessResult("更新汽车品牌成功");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarBrand> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        Pager pager = new Pager();
        logger.info("分页查询所有汽车品牌");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carBrandService.count());
        List<CarBrand> carBrandList = carBrandService.queryByPager(pager);
        return new Pager4EasyUI<CarBrand>(pager.getTotalRecords(), carBrandList);
    }

    @ResponseBody
    @RequestMapping(value = "car_brand_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCarBrandAll() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        logger.info("查询汽车品牌");
        List<CarBrand> carBrands = carBrandService.queryAll();
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (CarBrand carBrand : carBrands) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(carBrand.getBrandId());
            comboBox4EasyUI.setText(carBrand.getBrandName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

    @ResponseBody
    @RequestMapping(value = "name", method = RequestMethod.GET)
    public String queryName(String brandId) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        logger.info("根据id查询品牌名称");
        return carBrandService.queryNameById(brandId);
    }

    @ResponseBody
    @RequestMapping(value = "brandStatusModify", method = RequestMethod.GET)
    public ControllerResult brandStatusModify(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (status.equals("Y")) {
                logger.info("品牌冻结");
                carBrandService.inactive(id);
            } else if (status.equals("N")) {
                logger.info("品牌激活");
                carBrandService.active(id);
            }
            return ControllerResult.getSuccessResult("操作成功");
        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryByStatusPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarBrand> queryByStatusPager(@Param("status") String status, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carBrandService.statusCount(status));
        List<CarBrand> carBrandList = carBrandService.queryByBrandPager(status, pager);
        return new Pager4EasyUI<CarBrand>(pager.getTotalRecords(), carBrandList);
    }

}
