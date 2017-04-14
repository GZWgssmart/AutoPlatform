package com.gs.service.impl;

import com.gs.bean.MaterialReturn;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.MaterialReturnDAO;
import com.gs.service.MaterialReturnService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class MaterialReturnServiceImpl implements MaterialReturnService {

	@Resource
	private MaterialReturnDAO materialReturnDAO;

	public int insert(MaterialReturn materialReturn) { return materialReturnDAO.insert(materialReturn); }
	public int batchInsert(List<MaterialReturn> list) { return materialReturnDAO.batchInsert(list); }
	public int delete(MaterialReturn materialReturn) { return materialReturnDAO.delete(materialReturn); }
	public int deleteById(String id) {
        return materialReturnDAO.deleteById(id);
    }
	public int batchDelete(List<MaterialReturn> list) { return materialReturnDAO.batchDelete(list); }
	public int update(MaterialReturn materialReturn) { return materialReturnDAO.update(materialReturn); }
	public int batchUpdate(List<MaterialReturn> list) { return materialReturnDAO.batchUpdate(list); }
	public List<MaterialReturn> queryAll() { return materialReturnDAO.queryAll(); }
	public List<MaterialReturn> queryByStatus(String status) { return materialReturnDAO.queryByStatus(status); }
	public MaterialReturn query(MaterialReturn materialReturn) { return materialReturnDAO.query(materialReturn); }
	public MaterialReturn queryById(String id) { return materialReturnDAO.queryById(id); }
	public List<MaterialReturn> queryByPager(Pager pager) { return materialReturnDAO.queryByPager(pager); }
	public int count() { return materialReturnDAO.count(); }
	public int inactive(String id) { return materialReturnDAO.inactive(id); }
	public int active(String id) { return materialReturnDAO.active(id); }

}