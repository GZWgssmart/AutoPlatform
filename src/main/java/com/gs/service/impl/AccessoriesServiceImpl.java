package com.gs.service.impl;

import com.gs.bean.Accessories;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.AccessoriesDAO;
import com.gs.service.AccessoriesService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:53
*/
@Service
public class AccessoriesServiceImpl implements AccessoriesService {

	@Resource
	private AccessoriesDAO accessoriesDAO;

	public int insert(Accessories accessories) { return accessoriesDAO.insert(accessories); }
	public int batchInsert(List<Accessories> list) { return accessoriesDAO.batchInsert(list); }
	public int delete(Accessories accessories) { return accessoriesDAO.delete(accessories); }
	public int deleteById(String id) {
        return accessoriesDAO.deleteById(id);
    }
	public int batchDelete(List<Accessories> list) { return accessoriesDAO.batchDelete(list); }
	public int update(Accessories accessories) { return accessoriesDAO.update(accessories); }
	public int batchUpdate(List<Accessories> list) { return accessoriesDAO.batchUpdate(list); }
	public List<Accessories> queryAll() { return accessoriesDAO.queryAll(); }
	public List<Accessories> queryByStatus(String status) { return accessoriesDAO.queryByStatus(status); }
	public Accessories query(Accessories accessories) { return accessoriesDAO.query(accessories); }
	public Accessories queryById(String id) { return accessoriesDAO.queryById(id); }
	public List<Accessories> queryByPager(Pager pager) { return accessoriesDAO.queryByPager(pager); }
	public int count() { return accessoriesDAO.count(); }
	public int inactive(String id) { return accessoriesDAO.inactive(id); }
	public int active(String id) { return accessoriesDAO.active(id); }

}