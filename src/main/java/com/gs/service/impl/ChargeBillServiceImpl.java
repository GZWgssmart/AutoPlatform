package com.gs.service.impl;

import com.gs.bean.ChargeBill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.ChargeBillDAO;
import com.gs.service.ChargeBillService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class ChargeBillServiceImpl implements ChargeBillService {

	@Resource
	private ChargeBillDAO chargeBillDAO;

	public int insert(ChargeBill chargeBill) { return chargeBillDAO.insert(chargeBill); }
	public int batchInsert(List<ChargeBill> list) { return chargeBillDAO.batchInsert(list); }
	public int delete(ChargeBill chargeBill) { return chargeBillDAO.delete(chargeBill); }
	public int deleteById(String id) {
        return chargeBillDAO.deleteById(id);
    }
	public int batchDelete(List<ChargeBill> list) { return chargeBillDAO.batchDelete(list); }
	public int update(ChargeBill chargeBill) { return chargeBillDAO.update(chargeBill); }
	public int batchUpdate(List<ChargeBill> list) { return chargeBillDAO.batchUpdate(list); }
	public List<ChargeBill> queryAll() { return chargeBillDAO.queryAll(); }
	public List<ChargeBill> queryByStatus(String status) { return chargeBillDAO.queryByStatus(status); }
	public ChargeBill query(ChargeBill chargeBill) { return chargeBillDAO.query(chargeBill); }
	public ChargeBill queryById(String id) { return chargeBillDAO.queryById(id); }
	public List<ChargeBill> queryByPager(Pager pager) { return chargeBillDAO.queryByPager(pager); }
	public int count() { return chargeBillDAO.count(); }
	public int inactive(String id) { return chargeBillDAO.inactive(id); }
	public int active(String id) { return chargeBillDAO.active(id); }

	public int countByStatus(String status) {
		return chargeBillDAO.countByStatus(status);
	}

	public List<ChargeBill> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status) {
		return chargeBillDAO.queryPagerByStatus(pager, status);
	}

	public int countByCondition(ChargeBill chargeBill) {
		return chargeBillDAO.countByCondition(chargeBill);
	}

	public List<ChargeBill> queryPagerByCondition(@Param("pager") Pager pager, @Param("chargeBill") ChargeBill chargeBill) {
		return chargeBillDAO.queryPagerByCondition(pager, chargeBill);
	}

	@Override
	public List<ChargeBill> queryByDefault(String maintainOrFix, String userId) {
		return chargeBillDAO.queryByDefault(maintainOrFix,userId);
	}

	@Override
	public List<ChargeBill> queryByCondition(String startTime, String endTime, String maintainOrFix, String type, String userId) {
		return chargeBillDAO.queryByCondition(startTime,endTime,maintainOrFix,type,userId);
	}
}