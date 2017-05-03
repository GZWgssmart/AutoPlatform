package com.gs.service.impl;

import com.gs.bean.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.UserDAO;
import com.gs.service.UserService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDAO userDAO;

	public int insert(User user) { return userDAO.insert(user); }
	public int batchInsert(List<User> list) { return userDAO.batchInsert(list); }
	public int delete(User user) { return userDAO.delete(user); }
	public int deleteById(String id) {
        return userDAO.deleteById(id);
    }
	public int batchDelete(List<User> list) { return userDAO.batchDelete(list); }
	public int update(User user) { return userDAO.update(user); }
	public int batchUpdate(List<User> list) { return userDAO.batchUpdate(list); }
	public List<User> queryAll() { return userDAO.queryAll(); }
	public List<User> queryByStatus(String status) { return userDAO.queryByStatus(status); }
	public User query(User user) { return userDAO.query(user); }
	public User queryById(String id) { return userDAO.queryById(id); }
	public List<User> queryByPager(Pager pager) { return userDAO.queryByPager(pager); }
	public int count() { return userDAO.count(); }
	public int inactive(String id) { return userDAO.inactive(id); }
	public int active(String id) { return userDAO.active(id); }

	public List<User> queryByUser(Pager pager, String companyId) {
		return userDAO.queryByUser(pager,companyId);
	}

	public int countByUser(String companyId) {
		return userDAO.countByUser(companyId);
	}

	public List<User> queryByAdminPager(Pager pager) {
		return userDAO.queryByAdminPager(pager);
	}

	public int countAdmin() {
		return userDAO.countAdmin();
	}

	public List<User> queryByCompanyAdminPager(Pager pager) {
		return userDAO.queryByCompanyAdminPager(pager);
	}

	public int countCompanyAdmin() {
		return userDAO.countCompanyAdmin();
	}

	public List<User> queryBySystemAdminPager(Pager pager) {
		return userDAO.queryBySystemAdminPager(pager);
	}

	public int countSystemAdmin() {
		return userDAO.countSystemAdmin();
	}

	public User queryLogin(User user) {
		return userDAO.queryLogin(user);
	}
}