package com.gs.service;

import com.gs.bean.CarPlate;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface CarPlateService extends BaseService<String, CarPlate>{
    public List<CarPlate> byStatusPager(String status,Pager pager);
    public int countStatus(String status);
}