package com.pwpew.dao;

import com.pwpew.entity.TUser;
import com.pwpew.entity.TVolunteer;
import com.pwpew.modeldriven.VolunteerMd;

import java.util.List;

public interface VolunteerDao {
    //增删改查
    //查询所有用户
    public List<TVolunteer> findVolunteer();

    public TVolunteer getVolunteer(int volId);

    public void updateVolunteer(TVolunteer volunteer);

    public void deleteVolunteer(TVolunteer volunteer);

    public void insertVolunteer(TVolunteer volunteer);

    //分页查询用户
    public List<TVolunteer> findUserByPage(VolunteerMd volMd, int firstResult, int maxResults);

    //查询用户总数，用于分页
    public Long findUserCount(VolunteerMd volMd);
}
