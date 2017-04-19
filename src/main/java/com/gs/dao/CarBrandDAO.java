package com.gs.dao;

import com.gs.bean.CarBrand;
import org.springframework.stereotype.Repository;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface CarBrandDAO extends BaseDAO<String, CarBrand>{
    public String queryNameById(String brandId);
}