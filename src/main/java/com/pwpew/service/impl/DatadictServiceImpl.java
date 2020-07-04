package com.pwpew.service.impl;

import com.pwpew.dao.DatadictDao;
import com.pwpew.entity.TDatadict;
import com.pwpew.service.DatadictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/21 - 17:34
 * @commet:
 */
@Service("datadictService")
public class DatadictServiceImpl implements DatadictService {

    @Autowired
    private DatadictDao datadictDao;

    @Override
    public List<TDatadict> findByDictTypeCode(String dictTypeCode) {
        return datadictDao.findByDictTypeCode(dictTypeCode);
    }
}
