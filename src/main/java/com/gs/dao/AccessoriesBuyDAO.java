package com.gs.dao;

import com.gs.bean.AccessoriesBuy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:35:15
 */
@Repository
public interface AccessoriesBuyDAO extends BaseDAO<String, AccessoriesBuy> {
    public int batchDeleteAcc(@Param("ids") String[] ids);
}