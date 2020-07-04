package com.pwpew.service;

import com.pwpew.entity.TUser;
import com.pwpew.modeldriven.UserMd;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/8 - 9:42
 * @commet:
 */
public interface UserService {
    public List<TUser> findUser();
    public TUser getUserById(int userid);
    public void updateUser(int userid,UserMd user);
    public void deleteUserById(int userid);
    public void insertUser(UserMd user) throws InvocationTargetException, IllegalAccessException;

    //分页查询用户
    public List<TUser> findUserByPage(UserMd user, int firstResult, int maxResults);
    //查询用户总数，用于分页
    public Long findUserCount(UserMd user);
    public TUser findUserByPostId(int postId);
    public int updateUserOfAccount(TUser user);

    public TUser getUserByEmail(String email);

    public void updateUserPasswordById(int userId,String password);
}
