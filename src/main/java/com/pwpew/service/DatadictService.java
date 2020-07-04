package com.pwpew.service;

import com.pwpew.entity.TDatadict;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/21 - 17:33
 * @commet:
 */
public interface DatadictService {

    //通过数据字典类别代码查询数据字典
    public List<TDatadict> findByDictTypeCode(String dictTypeCode);
}
