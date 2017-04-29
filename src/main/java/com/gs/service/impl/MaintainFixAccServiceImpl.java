package com.gs.service.impl;

import com.gs.bean.MaintainFixAcc;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.MaintainFixAccDAO;
import com.gs.service.MaintainFixAccService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class MaintainFixAccServiceImpl implements MaintainFixAccService {

	@Resource
	private MaintainFixAccDAO maintainFixAccDAO;

	public int insert(MaintainFixAcc maintainFixAcc) { return maintainFixAccDAO.insert(maintainFixAcc); }
	public int batchInsert(List<MaintainFixAcc> list) { return maintainFixAccDAO.batchInsert(list); }
	public int delete(MaintainFixAcc maintainFixAcc) { return maintainFixAccDAO.delete(maintainFixAcc); }
	public int deleteById(String id) {
        return maintainFixAccDAO.deleteById(id);
    }
	public int batchDelete(List<MaintainFixAcc> list) { return maintainFixAccDAO.batchDelete(list); }
	public int update(MaintainFixAcc maintainFixAcc) { return maintainFixAccDAO.update(maintainFixAcc); }
	public int batchUpdate(List<MaintainFixAcc> list) { return maintainFixAccDAO.batchUpdate(list); }
	public List<MaintainFixAcc> queryAll() { return maintainFixAccDAO.queryAll(); }
	public List<MaintainFixAcc> queryByStatus(String status) { return maintainFixAccDAO.queryByStatus(status); }
	public MaintainFixAcc query(MaintainFixAcc maintainFixAcc) { return maintainFixAccDAO.query(maintainFixAcc); }
	public MaintainFixAcc queryById(String id) { return maintainFixAccDAO.queryById(id); }
	public List<MaintainFixAcc> queryByPager(Pager pager) { return maintainFixAccDAO.queryByPager(pager); }
	public int count() { return maintainFixAccDAO.count(); }
	public int inactive(String id) { return maintainFixAccDAO.inactive(id); }
	public int active(String id) { return maintainFixAccDAO.active(id); }

	public List<MaintainFixAcc> queryAllByMaintainId(String[] ids) {
		return maintainFixAccDAO.queryAllByMaintainId(ids);
	}
}