package com.gs.service.impl;

import com.gs.bean.MaintainDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.MaintainDetailDAO;
import com.gs.service.MaintainDetailService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class MaintainDetailServiceImpl implements MaintainDetailService {

	@Resource
	private MaintainDetailDAO maintainDetailDAO;

	public int insert(MaintainDetail maintainDetail) { return maintainDetailDAO.insert(maintainDetail); }
	public int batchInsert(List<MaintainDetail> list) { return maintainDetailDAO.batchInsert(list); }
	public int delete(MaintainDetail maintainDetail) { return maintainDetailDAO.delete(maintainDetail); }
	public int deleteById(String id) {
        return maintainDetailDAO.deleteById(id);
    }
	public int batchDelete(List<MaintainDetail> list) { return maintainDetailDAO.batchDelete(list); }
	public int update(MaintainDetail maintainDetail) { return maintainDetailDAO.update(maintainDetail); }
	public int batchUpdate(List<MaintainDetail> list) { return maintainDetailDAO.batchUpdate(list); }
	public List<MaintainDetail> queryAll() { return maintainDetailDAO.queryAll(); }
	public List<MaintainDetail> queryByStatus(String status) { return maintainDetailDAO.queryByStatus(status); }
	public MaintainDetail query(MaintainDetail maintainDetail) { return maintainDetailDAO.query(maintainDetail); }
	public MaintainDetail queryById(String id) { return maintainDetailDAO.queryById(id); }
	public List<MaintainDetail> queryByPager(Pager pager) { return maintainDetailDAO.queryByPager(pager); }
	public int count() { return maintainDetailDAO.count(); }
	public int inactive(String id) { return maintainDetailDAO.inactive(id); }
	public int active(String id) { return maintainDetailDAO.active(id); }

	@Override
	public int countByRecordId(String recordId) {
		return maintainDetailDAO.countByRecordId(recordId);
	}

	@Override
	public List<MaintainDetail> queryPagerByRecordId(@Param("pager") Pager pager, @Param("recordId") String recordId) {
		return maintainDetailDAO.queryPagerByRecordId(pager, recordId);
	}
}