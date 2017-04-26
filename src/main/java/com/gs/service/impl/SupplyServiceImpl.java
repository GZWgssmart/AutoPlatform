package com.gs.service.impl;

import com.gs.bean.Supply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.SupplyDAO;
import com.gs.service.SupplyService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class SupplyServiceImpl implements SupplyService {

	@Resource
	private SupplyDAO supplyDAO;

	public int insert(Supply supply) { return supplyDAO.insert(supply); }
	public int batchInsert(List<Supply> list) { return supplyDAO.batchInsert(list); }
	public int delete(Supply supply) { return supplyDAO.delete(supply); }
	public int deleteById(String id) {
        return supplyDAO.deleteById(id);
    }
	public int batchDelete(List<Supply> list) { return supplyDAO.batchDelete(list); }
	public int update(Supply supply) { return supplyDAO.update(supply); }
	public int batchUpdate(List<Supply> list) { return supplyDAO.batchUpdate(list); }
	public List<Supply> queryAll() { return supplyDAO.queryAll(); }
	public List<Supply> queryByStatus(String status) { return supplyDAO.queryByStatus(status); }
	public Supply query(Supply supply) { return supplyDAO.query(supply); }
	public Supply queryById(String id) { return supplyDAO.queryById(id); }
	public List<Supply> queryByPager(Pager pager) { return supplyDAO.queryByPager(pager); }
	public int count() { return supplyDAO.count(); }
	public int inactive(String id) { return supplyDAO.inactive(id); }
	public int active(String id) { return supplyDAO.active(id); }

	public int countByStatus(String status) {
		return supplyDAO.countByStatus(status);
	}

	public List<Supply> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status) {
		return supplyDAO.queryPagerByStatus(pager, status);
	}
}