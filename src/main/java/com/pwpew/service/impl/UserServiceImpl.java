package com.pwpew.service.impl;

import com.pwpew.dao.UserDao;
import com.pwpew.entity.TUser;
import com.pwpew.modeldriven.UserMd;
import com.pwpew.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/8 - 10:08
 * @commet:
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<TUser> findUser() {
        return userDao.findUser();
    }

    @Override
    public TUser getUserById(int userid) {
        TUser user = new TUser();
        user.setUserId(userid);
        return userDao.getUser(user);
    }

    @Override
    public void updateUser(int userid, UserMd user) {
        if (user == null) {
            throw new RuntimeException("参数不合法");
        }

        TUser tUser = new TUser();
        // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
        BeanUtils.copyProperties(user, tUser);
        tUser.setUserId(userid);

        if (StringUtils.isNotEmpty(tUser.getUsername()) && StringUtils.isNotEmpty(tUser.getUserPassword())
                && StringUtils.isNotEmpty(tUser.getUserGender()) && StringUtils.isNotEmpty(tUser.getUserCity())
                && StringUtils.isNotEmpty(tUser.getUserProvince()) && StringUtils.isNotEmpty(tUser.getUserPhone())
                && tUser.getUserAge() >= 0) {

            userDao.updateUser(tUser);
        } else {
            throw new RuntimeException("必要参数不能为空");
        }

    }

    @Override
    public void deleteUserById(int userid) {

        TUser user = new TUser();
        user.setUserId(userid);

        userDao.deleteUser(user);
    }

    @Override
    public void insertUser(UserMd user) {
        if (user == null) {
            throw new RuntimeException("参数不合法");
        }

        TUser tUser = new TUser();
        // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
        BeanUtils.copyProperties(user, tUser);

        if (StringUtils.isNotEmpty(tUser.getUsername()) && StringUtils.isNotEmpty(tUser.getUserPassword())
                && StringUtils.isNotEmpty(tUser.getUserGender()) && StringUtils.isNotEmpty(tUser.getUserCity())
                && StringUtils.isNotEmpty(tUser.getUserProvince()) && StringUtils.isNotEmpty(tUser.getUserPhone())
                && tUser.getUserAge() >= 0 && StringUtils.isNotEmpty(tUser.getUserNickname())) {

            userDao.insertUser(tUser);
        } else {
            throw new RuntimeException("必要参数不能为空");
        }


    }

    @Override
    public List<TUser> findUserByPage(UserMd user, int firstResult, int maxResults) {
        return userDao.findUserByPage(user, firstResult, maxResults);
    }

    @Override
    public Long findUserCount(UserMd user) {
        return userDao.findUserCount(user);
    }

    @Override
    public TUser findUserByPostId(int postId) {
        return userDao.findUserByPostId(postId);
    }

    @Override
    public int updateUserOfAccount(TUser user) {
        return userDao.updateUserOfAccount(user);
    }

    @Override
    public TUser getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public void updateUserPasswordById(int userId, String password) {

        //用id查询用户
        TUser user = userDao.getUserById(userId);
        //修改该用户的密码
        user.setUserPassword(password);

        userDao.updateUser(user);
    }
}
