package com.gs.service.impl;

import com.gs.bean.CarBrand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.CarBrandDAO;
import com.gs.service.CarBrandService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class CarBrandServiceImpl implements CarBrandService {

	@Resource
	private CarBrandDAO carBrandDAO;

	public int insert(CarBrand carBrand) { return carBrandDAO.insert(carBrand); }
	public int batchInsert(List<CarBrand> list) { return carBrandDAO.batchInsert(list); }
	public int delete(CarBrand carBrand) { return carBrandDAO.delete(carBrand); }
	public int deleteById(String id) {
        return carBrandDAO.deleteById(id);
    }
	public int batchDelete(List<CarBrand> list) { return carBrandDAO.batchDelete(list); }
	public int update(CarBrand carBrand) { return carBrandDAO.update(carBrand); }
	public int batchUpdate(List<CarBrand> list) { return carBrandDAO.batchUpdate(list); }
	public List<CarBrand> queryAll() { return carBrandDAO.queryAll(); }
	public List<CarBrand> queryByStatus(String status) { return carBrandDAO.queryByStatus(status); }
	public CarBrand query(CarBrand carBrand) { return carBrandDAO.query(carBrand); }
	public CarBrand queryById(String id) { return carBrandDAO.queryById(id); }
	public List<CarBrand> queryByPager(Pager pager) { return carBrandDAO.queryByPager(pager); }
	public int count() { return carBrandDAO.count(); }
	public int inactive(String id) { return carBrandDAO.inactive(id); }
	public int active(String id) { return carBrandDAO.active(id); }

	public String queryNameById(String brandId) {
		return carBrandDAO.queryNameById(brandId);
	}

	@Override
	public List<CarBrand> queryByBrandPager(String status, Pager pager) {
		return carBrandDAO.queryByBrandPager(status, pager);
	}

	@Override
	public int statusCount(String status) {
		return carBrandDAO.statusCount(status);
	}
}