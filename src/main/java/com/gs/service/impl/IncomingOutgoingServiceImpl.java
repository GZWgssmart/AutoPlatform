package com.gs.service.impl;

import com.gs.bean.IncomingOutgoing;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.IncomingOutgoingDAO;
import com.gs.service.IncomingOutgoingService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class IncomingOutgoingServiceImpl implements IncomingOutgoingService {

	@Resource
	private IncomingOutgoingDAO incomingOutgoingDAO;

	public int insert(IncomingOutgoing incomingOutgoing) { return incomingOutgoingDAO.insert(incomingOutgoing); }
	public int batchInsert(List<IncomingOutgoing> list) { return incomingOutgoingDAO.batchInsert(list); }
	public int delete(IncomingOutgoing incomingOutgoing) { return incomingOutgoingDAO.delete(incomingOutgoing); }
	public int deleteById(String id) {
        return incomingOutgoingDAO.deleteById(id);
    }
	public int batchDelete(List<IncomingOutgoing> list) { return incomingOutgoingDAO.batchDelete(list); }
	public int update(IncomingOutgoing incomingOutgoing) { return incomingOutgoingDAO.update(incomingOutgoing); }
	public int batchUpdate(List<IncomingOutgoing> list) { return incomingOutgoingDAO.batchUpdate(list); }
	public List<IncomingOutgoing> queryAll() { return incomingOutgoingDAO.queryAll(); }
	public List<IncomingOutgoing> queryByStatus(String status) { return incomingOutgoingDAO.queryByStatus(status); }
	public IncomingOutgoing query(IncomingOutgoing incomingOutgoing) { return incomingOutgoingDAO.query(incomingOutgoing); }
	public IncomingOutgoing queryById(String id) { return incomingOutgoingDAO.queryById(id); }
	public List<IncomingOutgoing> queryByPager(Pager pager) { return incomingOutgoingDAO.queryByPager(pager); }
	public int count() { return incomingOutgoingDAO.count(); }
	public int inactive(String id) { return incomingOutgoingDAO.inactive(id); }
	public int active(String id) { return incomingOutgoingDAO.active(id); }

}