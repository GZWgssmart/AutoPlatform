package com.gs.service.impl;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesBuy;
import com.gs.common.bean.Pager;
import com.gs.dao.AccessoriesBuyDAO;
import com.gs.service.AccessoriesBuyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:58:53
 */
@Service
public class AccessoriesBuyServiceImpl implements AccessoriesBuyService {

    @Resource
    private AccessoriesBuyDAO accessoriesBuyDAO;

    public int insert(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.insert(accessoriesBuy);
    }

    public int batchInsert(List<AccessoriesBuy> list) {
        return accessoriesBuyDAO.batchInsert(list);
    }

    public int delete(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.delete(accessoriesBuy);
    }

    public int deleteById(String id) {
        return accessoriesBuyDAO.deleteById(id);
    }

    public int batchDelete(List<AccessoriesBuy> list) {
        return accessoriesBuyDAO.batchDelete(list);
    }

    public int update(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.update(accessoriesBuy);
    }

    public int batchUpdate(List<AccessoriesBuy> list) {
        return accessoriesBuyDAO.batchUpdate(list);
    }

    public List<AccessoriesBuy> queryAll() {
        return accessoriesBuyDAO.queryAll();
    }

    public List<AccessoriesBuy> queryByStatus(String status) {
        return accessoriesBuyDAO.queryByStatus(status);
    }

    public AccessoriesBuy query(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.query(accessoriesBuy);
    }

    public AccessoriesBuy queryById(String id) {
        return accessoriesBuyDAO.queryById(id);
    }

    public List<AccessoriesBuy> queryByPager(Pager pager) {
        return accessoriesBuyDAO.queryByPager(pager);
    }

    public int count() {
        return accessoriesBuyDAO.count();
    }

    public int inactive(String id) {
        return accessoriesBuyDAO.inactive(id);
    }

    public int active(String id) {
        return accessoriesBuyDAO.active(id);
    }


    public int batchDeleteAcc(String[] ids) {
        return accessoriesBuyDAO.batchDeleteAcc(ids);
    }

    public List<Accessories> queryByCheckStates(String checkState) {
        return accessoriesBuyDAO.queryByCheckStates(checkState);
    }

    public int countByBuyState(String buyState) {
        return accessoriesBuyDAO.countByBuyState(buyState);
    }

    public int countByCheckState(String checkState) {
        return accessoriesBuyDAO.countByCheckState(checkState);
    }

    public List<AccessoriesBuy> queryByBuyStatePager(Pager pager) {
        return accessoriesBuyDAO.queryByBuyStatePager(pager);
    }

    public List<AccessoriesBuy> queryByCheckStatePager(Pager pager) {
        return accessoriesBuyDAO.queryByCheckStatePager(pager);
    }

    public int countByAccName(String accName) {
        return accessoriesBuyDAO.countByAccName(accName);
    }

    public List<AccessoriesBuy> queryByAccNamePager(Pager pager, String accName) {
        return accessoriesBuyDAO.queryByAccNamePager(pager, accName);
    }


}