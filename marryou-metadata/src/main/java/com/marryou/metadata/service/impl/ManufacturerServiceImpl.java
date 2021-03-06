package com.marryou.metadata.service.impl;


import com.marryou.metadata.dao.ManufacturerDao;
import com.marryou.metadata.entity.ManufacturerEntity;
import com.marryou.metadata.entity.OperateLogEntity;
import com.marryou.metadata.enums.LogTypeEnum;
import com.marryou.metadata.enums.OperateTypeEnum;
import com.marryou.metadata.service.ManufacturerService;
import com.marryou.metadata.service.OperateLogService;
import com.sun.tools.doclets.formats.html.resources.standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by linhy on 2018/6/6.
 */
@Service
public class ManufacturerServiceImpl extends AbsBaseService<ManufacturerEntity, ManufacturerDao> implements ManufacturerService {

    @Autowired
    private OperateLogService operateLogService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMft(ManufacturerEntity mft, String logContent, OperateTypeEnum type, String operate) {
        this.save(mft);
        operateLogService.save(new OperateLogEntity(logContent, type, mft.getId(),
                LogTypeEnum.MANUFACTURE,operate, new Date()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMft(ManufacturerEntity mft, String logContent, OperateTypeEnum type, String operate) {
        operateLogService.save(new OperateLogEntity(logContent, type, mft.getId(),
                LogTypeEnum.MANUFACTURE,operate, new Date()));
        this.delete(mft.getId());
    }
}
