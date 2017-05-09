package com.gs.dao;

import com.gs.bean.AccessoriesSale;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:35:15
 */
@Repository
public interface AccessoriesSaleDAO extends BaseDAO<String, AccessoriesSale> {
    public int queryByUserIdIsSameResult(@Param("id") String id, @Param("userName") String userName);
}