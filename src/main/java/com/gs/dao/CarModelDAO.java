package com.gs.dao;

import com.gs.bean.CarModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface CarModelDAO extends BaseDAO<String, CarModel>{

    public List<CarModel> queryByBrandId(String brandId);
}