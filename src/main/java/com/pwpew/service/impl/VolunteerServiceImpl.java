package com.pwpew.service.impl;

import com.pwpew.dao.VolunteerDao;
import com.pwpew.entity.TVolunteer;
import com.pwpew.modeldriven.VolunteerMd;
import com.pwpew.service.VolunteerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Yani
 * @date 2020/5/24 - 10:08
 * @commet:
 */
@Service("volunteerService")
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerDao volunteerDao;

    @Override
    public List<TVolunteer> findVolunteer() {
        return volunteerDao.findVolunteer();
    }

    @Override
    public TVolunteer getVolunteerById(int volId) {
        return volunteerDao.getVolunteer(volId);
    }

    @Override
    public void updateVolunteer(int volId, VolunteerMd volunteer) {
        if (volunteer == null) {
            throw new RuntimeException("参数不合法");
        }

        TVolunteer tVolunteer = new TVolunteer();
        // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
        BeanUtils.copyProperties(volunteer, tVolunteer);
        tVolunteer.setVolId(volId);

        //            关键字段不能为空且长度大于0
        if (StringUtils.isNotEmpty(tVolunteer.getVolname()) && StringUtils.isNotEmpty(tVolunteer.getVolIdCard())
                && StringUtils.isNotEmpty(tVolunteer.getVolPhone()) && StringUtils.isNotEmpty(tVolunteer.getVolSex())
                && StringUtils.isNotEmpty(tVolunteer.getVolProvince()) && StringUtils.isNotEmpty(tVolunteer.getVolCity())
                && StringUtils.isNotEmpty(tVolunteer.getVoldetailAddr()) && StringUtils.isNotEmpty(tVolunteer.getVolEmail())
                && StringUtils.isNotEmpty(tVolunteer.getVolQq())) {
            volunteerDao.updateVolunteer(tVolunteer);
        } else {
            throw new RuntimeException("必要参数不能为空");
        }
    }

    @Override
    public void deleteVolunteer(TVolunteer volunteer) {

        volunteerDao.deleteVolunteer(volunteer);
    }

    @Override
    public void insertVolunteer(VolunteerMd volunteer) throws InvocationTargetException, IllegalAccessException {
            if(volunteer==null){
                throw new RuntimeException("参数不合法");
            }

        TVolunteer tVolunteer = new TVolunteer();
        // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
        BeanUtils.copyProperties(volunteer, tVolunteer);

        //            关键字段不能为空且长度大于0
        if (StringUtils.isNotEmpty(tVolunteer.getVolname()) && StringUtils.isNotEmpty(tVolunteer.getVolIdCard())
                && StringUtils.isNotEmpty(tVolunteer.getVolPhone()) && StringUtils.isNotEmpty(tVolunteer.getVolSex())
                && StringUtils.isNotEmpty(tVolunteer.getVolProvince()) && StringUtils.isNotEmpty(tVolunteer.getVolCity())

                && StringUtils.isNotEmpty(tVolunteer.getVolQq()) ) {
// && StringUtils.isNotEmpty(tVolunteer.getVoldetailAddr()) && StringUtils.isNotEmpty(tVolunteer.getVolEmail())
            volunteerDao.insertVolunteer(tVolunteer);
        } else {
            throw new RuntimeException("必要参数不能为空");
        }

    }

    @Override
    public List<TVolunteer> findVolunteerByPage(VolunteerMd volunteer, int firstResult, int maxResults) {
        return volunteerDao.findUserByPage(volunteer,firstResult,maxResults);
    }

    @Override
    public Long findVolunteerCount(VolunteerMd volunteer) {
        return volunteerDao.findUserCount(volunteer);
    }
}
