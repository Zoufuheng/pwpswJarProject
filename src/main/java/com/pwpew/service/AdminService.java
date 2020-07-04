package com.pwpew.service;

import com.pwpew.entity.TAdministrator;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/13 - 13:00
 * @commet:
 */
public interface AdminService {
    //增删改查
    //查询所有管理员
    public List<TAdministrator> findAdmin();
    public TAdministrator getAdminById(int id);
    public void updateAdmin(TAdministrator admin);
}
