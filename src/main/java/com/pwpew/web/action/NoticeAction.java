package com.pwpew.web.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pwpew.entity.TNotice;
import com.pwpew.modeldriven.NoticeMd;
import com.pwpew.service.NoticeService;
import com.pwpew.utils.CommonUtil;
import com.pwpew.utils.FastJsonUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author miaoyin
 * @date 2020/5/13 - 10:15
 * @commet:通告使用的action
 */
@Controller("noticeAction")
@Scope("prototype")
public class NoticeAction extends ActionSupport implements ModelDriven<NoticeMd> {

    //注入service
    @Autowired
    private NoticeService noticeService;

    //创建模型驱动
    private NoticeMd noticeMd = new NoticeMd();

    @Override
    public NoticeMd getModel() {
        return noticeMd;
    }


    /*获取通告列表信息并跳转到通告列表界面*/
    public String noticeList(){
        int page = noticeMd.getPage();
        int rows = noticeMd.getRows();

        int firestResult = (page-1)*rows;

        List<TNotice> list = noticeService.findNoticeByPage(noticeMd,firestResult,rows);
        int noticeCount = Math.toIntExact(noticeService.findNoticeCount(noticeMd));


        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("list",list);
        request.setAttribute("page",page);
        request.setAttribute("noticeCount",noticeCount);
        return "noticeSuccess";
    }

    /*获取通告内容并跳转到通告内容界面*/
    public String noticeMain(){
        int ntsId = noticeMd.getNtsId();
        int page = noticeMd.getPage();
        TNotice notice = noticeService.getNotice(ntsId);

        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("notice",notice);
        request.setAttribute("page",page);
        return "noticeMain";
    }

    /*获取上一条通告内容并跳转到通告内容界面*/
    public String Lastnotice(){
        int page = noticeMd.getPage();
        if(noticeService.findLastNotice(noticeMd,noticeMd.getNtsDate())==null){
            TNotice notice1 = noticeService.getNotice(noticeMd.getNtsId());
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("notice",notice1);
            request.setAttribute("page",page);
            return "Lastnotice";

        }
        TNotice notice = noticeService.findLastNotice(noticeMd,noticeMd.getNtsDate());
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("notice",notice);
        request.setAttribute("page",page);
        return "Lastnotice";
    }

    /*获取下一条通告内容并跳转到通告内容界面*/
    public String Nextnotice(){
        int page = noticeMd.getPage();
        if(noticeService.findNextNotice(noticeMd,noticeMd.getNtsDate())==null){
            TNotice notice1 = noticeService.getNotice(noticeMd.getNtsId());
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("notice",notice1);
            request.setAttribute("page",page);
            return "Nextnotice";

        }
        TNotice notice = noticeService.findNextNotice(noticeMd,noticeMd.getNtsDate());
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("notice",notice);
        request.setAttribute("page",page);
        return "Nextnotice";
    }


    /**
     * easyui查询通告
     */
    public void list() {

        //获取页码
        int page = noticeMd.getPage();
        //获取每页显示多少数据
        int rows = noticeMd.getRows();

        //计算开始记录下标
        int firstResult = (page - 1) * rows;

        //获取数据库中的通告总数
        Long total = noticeService.findNoticeCount(noticeMd);

        //分页查询通告
        List<TNotice> list = noticeService.findNoticeByPage(noticeMd, firstResult, rows);

        //将查询的通告进行分装，用于转换成json对象
        Map<String, Object> datagrid_result = new HashMap<String, Object>();
        datagrid_result.put("rows", list);
        datagrid_result.put("total", total);

        //转换成json对象
        HttpServletResponse response = ServletActionContext.getResponse();
        String jsonString = FastJsonUtil.toJSONString(datagrid_result);
        FastJsonUtil.write_json(response, jsonString);
    }

    /**
     * 对后台页面进行编辑修改的提交进行处理
     */
    public void editsubmit() {
        HttpServletResponse response = ServletActionContext.getResponse();
        try {

            TNotice notice = new TNotice();
            // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
            BeanUtils.copyProperties(noticeMd, notice);

            //如果传过来的内容长度大于了512就报错
            if (CommonUtil.getWordCount(notice.getNtsMain()) > 512) {

                String ajaxResult = FastJsonUtil.ajaxResult(false, "通告内容应小于等于256个汉字或512个字符,修改通告失败");
                FastJsonUtil.write_json(response, ajaxResult);
                return;
            }
            noticeService.updateNotice(notice);
        } catch (Exception e) {
            e.printStackTrace();
            String ajaxResult = FastJsonUtil.ajaxResult(false, "参数传输不合法,修改通告失败，所有字段均为必填字段！");
            FastJsonUtil.write_json(response, ajaxResult);
            return;
        }
        String ajaxResult = FastJsonUtil.ajaxResult(true, "修改通告成功");
        FastJsonUtil.write_json(response, ajaxResult);
    }

    /**
     * 处理新增通告
     */
    public void addsubmit() {
        HttpServletResponse response = ServletActionContext.getResponse();
        try {

            TNotice notice = new TNotice();
            // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
            BeanUtils.copyProperties(noticeMd, notice);

            //如果传过来的内容长度大于了512就报错
            if (CommonUtil.getWordCount(notice.getNtsMain()) > 512) {

                String ajaxResult = FastJsonUtil.ajaxResult(false, "通告内容应小于等于256个汉字或512个字符,添加通告失败");
                FastJsonUtil.write_json(response, ajaxResult);
                return;
            }
            noticeService.insertNotice(notice);
        } catch (Exception e) {
            e.printStackTrace();

            String ajaxResult = FastJsonUtil.ajaxResult(false, "参数传输不合法,添加通告失败");
            FastJsonUtil.write_json(response, ajaxResult);
            return;
        }
        String ajaxResult = FastJsonUtil.ajaxResult(true, "添加通告成功");
        FastJsonUtil.write_json(response, ajaxResult);
    }

    public void findNoticeById() {
        HttpServletResponse response = ServletActionContext.getResponse();
        TNotice notice = noticeService.getNotice(noticeMd.getNtsId());
        // 将上边的对象转json输出
        String jsonString = FastJsonUtil.toJSONString(notice);
        // 使用JsonFormatterAddPrefix工具方法将嵌套的json转成单层结构
        jsonString = FastJsonUtil.JsonFormatterAddPrefix(jsonString, "", null);
        FastJsonUtil.write_json(response, jsonString);
    }

    public void deleteNotice(){
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            TNotice notice = new TNotice();
            // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
            BeanUtils.copyProperties(noticeMd, notice);

            noticeService.deleteNotice(notice);
        }catch (Exception e){
            e.printStackTrace();
            String ajaxResult = FastJsonUtil.ajaxResult(false, "删除失败");
            FastJsonUtil.write_json(response, ajaxResult);
            return;
        }

        String jsonString = FastJsonUtil.ajaxResult(true,"删除成功");
        FastJsonUtil.write_json(response,jsonString);
    }



    public String latestNotice(){
        TNotice latestNotice = noticeService.findLatestNotice(noticeMd);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("latestNotice",latestNotice);
        return "latestNotice";
    }
}
