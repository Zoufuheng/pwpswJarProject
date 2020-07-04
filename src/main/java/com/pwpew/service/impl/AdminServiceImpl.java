package com.pwpew.service.impl;

import com.pwpew.dao.AdminDao;
import com.pwpew.entity.TAdministrator;
import com.pwpew.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/13 - 13:01
 * @commet:
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public List<TAdministrator> findAdmin() {
        return adminDao.findAdmin();
    }

    @Override
    public TAdministrator getAdminById(int id) {
        return adminDao.getAdminById(id);
    }

    @Override
    public void updateAdmin(TAdministrator admin) {
        adminDao.updateAdmin(admin);
    }


}
