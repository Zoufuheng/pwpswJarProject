package com.pwpew.dao;

import com.pwpew.entity.TAdministrator;
import com.pwpew.entity.TNotice;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/13 - 12:51
 * @commet:
 */
public interface AdminDao {

    //增删改查
    //查询所有管理员
    public List<TAdministrator> findAdmin();
    public TAdministrator getAdminById(int id);
    public void updateAdmin(TAdministrator admin);
}
