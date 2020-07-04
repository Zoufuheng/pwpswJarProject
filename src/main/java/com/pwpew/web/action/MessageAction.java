package com.pwpew.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pwpew.entity.TComment;
import com.pwpew.entity.TMessage;
import com.pwpew.entity.TNotice;
import com.pwpew.entity.TPost;
import com.pwpew.modeldriven.MessageMd;
import com.pwpew.service.MessageService;
import com.pwpew.utils.CommonUtil;
import com.pwpew.utils.FastJsonUtil;
import org.apache.commons.lang3.StringUtils;
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
 * @date 2020/5/16 - 13:38
 * @commet:
 */
@Controller("messageAction")
@Scope("prototype")
public class MessageAction extends ActionSupport implements ModelDriven<MessageMd> {

    @Autowired
    private MessageService messageService;

    private MessageMd messageMd = new MessageMd();

    @Override
    public MessageMd getModel() {
        return messageMd;
    }


    public void list() {

        Long total = messageService.findMessageCount(messageMd);

        //计算分页从哪个数据开始
        int firstResult = (messageMd.getPage() - 1) * messageMd.getRows();

        List<TMessage> list = messageService.findMessageByPage(messageMd, firstResult, messageMd.getRows());

        //将查询的通告进行分装，用于转换成json对象
        Map<String, Object> datagrid_result = new HashMap<String, Object>();
        datagrid_result.put("rows", list);
        datagrid_result.put("total", total);

        //转换成json对象
        HttpServletResponse response = ServletActionContext.getResponse();
        String jsonString = FastJsonUtil.toJSONString(datagrid_result);
        FastJsonUtil.write_json(response, jsonString);
    }

    public void findMessageById() {

        TMessage message = messageService.getMessageById(messageMd.getMsgId());


        // 将上边的对象转json输出
        HttpServletResponse response = ServletActionContext.getResponse();
        String jsonString = FastJsonUtil.toJSONString(message);
        // 使用JsonFormatterAddPrefix工具方法将嵌套的json转成单层结构
        jsonString = FastJsonUtil.JsonFormatterAddPrefix(jsonString, "", null);
        FastJsonUtil.write_json(response, jsonString);
    }

    public void editsubmit() {

        HttpServletResponse response = ServletActionContext.getResponse();
        TMessage message = new TMessage();
        try {

            // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
            BeanUtils.copyProperties(messageMd, message);

            if (CommonUtil.getWordCount(message.getMsgResMain()) > 256) {
                String ajaxResult = FastJsonUtil.ajaxResult(false, "留言内容应小于等于128个汉字或256个字符,回复留言失败");
                FastJsonUtil.write_json(response, ajaxResult);
                return;
            }

            if(StringUtils.isEmpty(message.getMsgResMain()) ){
                String ajaxResult = FastJsonUtil.ajaxResult(false, "留言内容不能为空");
                FastJsonUtil.write_json(response, ajaxResult);
                return;
            }


            messageService.updateMessage(message);

        } catch (Exception e) {
            e.printStackTrace();
            String ajaxResult = FastJsonUtil.ajaxResult(false, "参数传输不合法,内容不允许表情,回复留言失败");
            FastJsonUtil.write_json(response, ajaxResult);
            return;
        }

        String ajaxResult = FastJsonUtil.ajaxResult(true, "回复留言成功");
        FastJsonUtil.write_json(response, ajaxResult);

    }

    public void deleteMessage() {
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            TMessage message = new TMessage();
            // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
            BeanUtils.copyProperties(messageMd, message);

            messageService.deleteMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            String ajaxResult = FastJsonUtil.ajaxResult(false, "删除失败");
            FastJsonUtil.write_json(response, ajaxResult);
            return;
        }

        String jsonString = FastJsonUtil.ajaxResult(true, "删除成功");
        FastJsonUtil.write_json(response, jsonString);
    }

    public String leaveMessage(){

        TMessage message = new TMessage();
        // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
        BeanUtils.copyProperties(messageMd, message);
        HttpServletRequest request = ServletActionContext.getRequest();

        if((messageMd.getMsgMain().equals(null)) || (messageMd.getMsgMain().equals(""))){
            request.setAttribute("error", "留言内容不能为空");
            return "msgError";
        }else{
            messageService.insertMessage(message);
            request.setAttribute("messageLeft", "留言成功");
            return "leaveMessage";
        }
    }

    public String messageHistory(){

        int page = messageMd.getPage();
        int rows = 10;

        int firstResult = (page-1)*rows;

        List<TMessage> list = messageService.findMessageByUser(messageMd.getUser().getUserId(), firstResult, rows);

        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("messageList", list);

        return "messageHistory";
    }
}
