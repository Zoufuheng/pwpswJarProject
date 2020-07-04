package com.pwpew.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pwpew.entity.TComment;
import com.pwpew.entity.TPost;
import com.pwpew.modeldriven.CommentMd;
import com.pwpew.service.CommentService;
import com.pwpew.service.PostService;
import com.pwpew.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author QIN
 * @date 2020/5/26 - 19:38
 * @commet:
 */
@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends ActionSupport implements ModelDriven<TComment> {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    private CommentMd commentMd = new CommentMd();

    @Override
    public CommentMd getModel(){
        return commentMd;
    }

    public String insertComment() throws ParseException {

        TComment comment = new TComment();
        // 第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
        BeanUtils.copyProperties(commentMd, comment);

        TPost post = postService.getPostById(commentMd.getPostId());
        int count = commentMd.getCount();
        int totalPage = commentMd.getTotalPage();
        HttpServletRequest request = ServletActionContext.getRequest();

        if((commentMd.getComments().equals(null)) || (commentMd.getComments().equals(""))){
            request.setAttribute("error", "评论内容不能为空");
            request.setAttribute("post",post);
            request.setAttribute("comments", post.getComments());
            request.setAttribute("page", commentMd.getPage());
            request.setAttribute("count", count);
            request.setAttribute("totalPage", totalPage);
            return "insertError";
        }else{
            commentService.insertComment(comment);
            if(count%5 == 0){
                totalPage++;
            }
            count++;
            request.setAttribute("post",post);
            request.setAttribute("comments", post.getComments());
            request.setAttribute("page", commentMd.getPage());
            request.setAttribute("count", count);
            request.setAttribute("totalPage", totalPage);
            return "insertComment";
        }


    }

}
