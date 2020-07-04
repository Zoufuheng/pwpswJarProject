package com.pwpew.service.impl;

import com.pwpew.dao.NoticeDao;
import com.pwpew.entity.TNotice;
import com.pwpew.modeldriven.NoticeMd;
import com.pwpew.service.NoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/13 - 10:07
 * @commet:
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;


    @Override
    public List<TNotice> findNotice() {
        return null;
    }

    @Override
    public TNotice getNotice(int id) {
        TNotice notice = noticeDao.getNotice(id);
        if (notice.getAdministrator().getAdminPassword() != null) {
            notice.getAdministrator().setAdminPassword(null);
        }
        return notice;
    }

    @Override
    public void updateNotice(TNotice notice) {
        //如果传过来的notice为空，则直接进行事务控制rollback
        if (notice == null) {
            throw new RuntimeException("通告参数不合法");
        }

        //判断传进来的参数是否有空值，有就不进行持久化操作
        if (StringUtils.isEmpty(notice.getNtsHeadLine()) || StringUtils.isEmpty(notice.getNtsMain())
                || notice.getNtsDate() == null || notice.getAdministrator() == null) {
            throw new RuntimeException("通告参数不合法");
        }

        noticeDao.updateNotice(notice);
    }

    @Override
    public void deleteNotice(TNotice notice) {

        noticeDao.deleteNotice(notice);
    }

    @Override
    public void insertNotice(TNotice notice) {

        //如果传过来的notice为空，则直接进行事务控制rollback
        if (notice == null) {
            throw new RuntimeException("通告参数不合法");
        }

        //判断传进来的参数是否有空值，有就不进行持久化操作
        if (StringUtils.isEmpty(notice.getNtsHeadLine()) || StringUtils.isEmpty(notice.getNtsMain())
                || notice.getNtsDate() == null || notice.getAdministrator() == null) {
            throw new RuntimeException("通告参数不合法");
        }

        noticeDao.insertNotice(notice);
    }

    @Override
    public List<TNotice> findNoticeByPage(NoticeMd noticeMd, int firstResult, int maxResults) {
        return noticeDao.findNoticeByPage(noticeMd, firstResult, maxResults);
    }

    @Override
    public Long findNoticeCount(NoticeMd noticeMd) {
        return noticeDao.findNoticeCount(noticeMd);
    }

    @Override
    public TNotice findLatestNotice(NoticeMd noticeMd) {
        return noticeDao.findLatestNotice(noticeMd);
    }

    @Override
    public TNotice findLastNotice(NoticeMd noticeMd,Timestamp ntsDate) {
        return noticeDao.findLastNotice(noticeMd,ntsDate);
    }

    @Override
    public TNotice findNextNotice(NoticeMd noticeMd, Timestamp ntsDate) {
        return noticeDao.findNextNotice(noticeMd,ntsDate);
    }
}
