package com.gs.service.impl;

import com.gs.bean.info.MaterialUseInfo;
import com.gs.dao.MaterialUseInfoDAO;
import com.gs.service.MaterialUseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/5/15.
 */
@Service
public class MaterialUseInfoServiceImpl implements MaterialUseInfoService {

    @Resource
    private MaterialUseInfoDAO muiDAO;

    public void addByRecordIdMu(List<MaterialUseInfo> muis) {
        muiDAO.addByRecordIdMu(muis);
    }
}
