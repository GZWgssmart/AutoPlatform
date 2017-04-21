package com.gs.service;

import com.gs.bean.CarModel;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface CarModelService extends BaseService<String, CarModel>{

    public List<CarModel> queryByBrandId(String brandId);
}