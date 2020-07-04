package com.pwpew.web.action;

import java.io.File;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pwpew.entity.TAdministrator;
import com.pwpew.entity.TUser;
import com.pwpew.modeldriven.UserMd;
import com.pwpew.service.AdminService;
import com.pwpew.service.UserService;
import com.pwpew.utils.CommonUtil;
import com.pwpew.utils.FastJsonUtil;
import com.pwpew.utils.VerifyCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.apache.commons.io.FileUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author miaoyin
 * @date 2020/5/7 - 18:37
 * @commet: 用户使用的action
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<UserMd> {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    //模型驱动对象
    private UserMd userMd = new UserMd();

    @Override
    public UserMd getModel() {
        return userMd;
    }

    public void list() {

        //获取页码
        int page = userMd.getPage();
        //获取每页显示多少数据
        int rows = userMd.getRows();

        //计算开始记录下标
        int firstResult = (page - 1) * rows;
        //获取数据库中的帖子总数
        Long total = userService.findUserCount(userMd);
        //分页查询帖子
        List<TUser> list = userService.findUserByPage(userMd, firstResult, rows);
        //将查询的通告进行分装，用于转换成json对象
        Map<String, Object> datagrid_result = new HashMap<String, Object>();
        datagrid_result.put("rows", list);
        datagrid_result.put("total", total);

        //转换成json对象
        HttpServletResponse response = ServletActionContext.getResponse();
        String jsonString = FastJsonUtil.toJSONString(datagrid_result);
        FastJsonUtil.write_json(response, jsonString);
    }


    public void findUserById() {

        TUser user = userService.getUserById(userMd.getUserId());

        HttpServletResponse response = ServletActionContext.getResponse();
        String jsonString = FastJsonUtil.toJSONString(user);
        // 使用JsonFormatterAddPrefix工具方法将嵌套的json转成单层结构
        jsonString = FastJsonUtil.JsonFormatterAddPrefix(jsonString, "", null);
        FastJsonUtil.write_json(response, jsonString);
    }

    public void verifyUser() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();

      /*  String nowUsername = (String) session.getAttribute("username");
        String nowUserId = (String) session.getAttribute("userid");*/


        boolean flag = false;
        //从session中获取验证码
        String vcode = (String) request.getSession().getAttribute("vcode");


        //判断用户名是否为空
        if (StringUtils.isEmpty(userMd.getUsername())) {
            // 向客户端返回提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "用户名不能为空！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }

        //判断密码是否为空
        if (StringUtils.isEmpty(userMd.getUserPassword())) {
            // 向客户端返回提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "密码不能为空！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }

        //判断验证码是否正确
        if (vcode.equalsIgnoreCase(userMd.getVerifyCode()) == false) {
            // 向客户端返回提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "验证码错误！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }


        if (CommonUtil.getWordCount(userMd.getUsername()) > 32) {

            String ajaxResult = FastJsonUtil.ajaxResult(false, "用户名过长");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }

        if (CommonUtil.getWordCount(userMd.getUserPassword()) > 16) {

            String ajaxResult = FastJsonUtil.ajaxResult(false, "密码过长");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }

        //验证码正确后，核对用户名和密码
        ArrayList<TUser> list = (ArrayList<TUser>) userService.findUser();


        for (TUser tUser : list) {
            if (tUser.getUsername().equals(userMd.getUsername()) && tUser.getUserPassword().equals(userMd.getUserPassword())) {
                // 向客户返回成功提示
                String ajaxResult = FastJsonUtil.ajaxResult(true, "登录成功！");
                // 输出json
                FastJsonUtil.write_json(response, ajaxResult);

                //将用户名id和用户名放入session域中
                session.setAttribute("username", tUser.getUsername());
                session.setAttribute("userid", tUser.getUserId());
                session.setAttribute("usernickname", tUser.getUserNickname());
                session.setAttribute("userpicture", tUser.getUserPicture());
                //终止执行
                return;
            }

            //用于验证用户的用户名是否存在
            if (tUser.getUsername().equals(userMd.getUsername())) {
                flag = true;
            }
        }

        //如果用户名不存在，直接向前端进行提示
        if (flag == false) {
            // 向客户返回成功提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "用户名不存在,请先注册！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }


        /*if(nowUsername.equals(session.getAttribute("username")) && nowUserId.equals(session.getAttribute("userid"))){
            // 向客户返回成功提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "用户已经登录，登录失败！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }*/


        // 向客户返回成功提示
        String ajaxResult = FastJsonUtil.ajaxResult(false, "用户名或者密码错误！");
        // 输出json
        FastJsonUtil.write_json(response, ajaxResult);

    }


    /**
     * 实现验证码的获取
     *
     * @throws IOException
     */
    public void vcode() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        //创建验证码
        VerifyCode vc = new VerifyCode();
        //将验证码图片放入image中
        BufferedImage image = vc.getImage();
        //将验证码内容放入session中
        request.getSession().setAttribute("vcode", vc.getText());
        //静态方法，将验证码图片输出到前端
        VerifyCode.output(image, response.getOutputStream());
    }


    //修改用户信息
    public String submitpicture() {

        try {
            // 判断是否上传成功
            // 上传成功的图片，文件默认在tomcat的临时目录 中
            File picture = userMd.getPicture();
            // 上传文件的原始名称
            String pictureFileName = userMd.getPictureFileName();
            // 上传文件的类型
            String pictureContentType = userMd.getPictureContentType();

            if (picture != null && pictureFileName != null && !pictureFileName.equals("")) {
                // 服务器图片存储路径
                String filePath = "D:\\develop\\upload\\";
                // 扩展名，从原始名称中截取
                String fileName_extension = pictureFileName.substring(pictureFileName.lastIndexOf("."));

                // 为了保证服务器上图片目录中图片名称不重复将每个上传的图片重定义一个名称
                // 新文件名称
                String fileNameNew = UUID.randomUUID().toString() + fileName_extension;
                // 定义一个File
                File fileNew = new File(filePath + fileNameNew);

                // 将tomcat下临时目录中的文件picture拷贝或移动到fileNew
                // 使用拷贝，拷贝完成tomcat下临时目录中的文件自动删除了
                FileUtils.copyFile(picture, fileNew);

                // 在数据库中保存图片路径
                userMd.setUserPicture(fileNameNew);
            } else {
                userMd.setUserPicture(userMd.getUserPicture());
            }
            TUser user = new TUser();
            user.setUserId(userMd.getUserId());
            user.setUsername(userMd.getUsername());
            user.setUserNickname(userMd.getUserNickname());
            user.setUserGender(userMd.getUserGender());
            user.setUserAge(userMd.getUserAge());
            user.setUserPhone(userMd.getUserPhone());
            user.setUserProvince(userMd.getUserProvince());
            user.setUserCity(userMd.getUserCity());
            user.setUserPicture(userMd.getUserPicture());
            user.setEmail(userMd.getEmail());
            int i = userService.updateUserOfAccount(user);
            if (i > 0) {
                ServletActionContext.getRequest().setAttribute("message", "保存成功");
            } else {
                ServletActionContext.getRequest().setAttribute("message", "保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "userUpdateSuccess";
    }


    //    用户注册方法
    public String userRegister() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        HttpServletResponse response = ServletActionContext.getResponse();

        String vcode = (String) session.getAttribute("vcode");


        if (!userMd.getVerifyCode().equalsIgnoreCase(vcode)) {

            /*String ajaxResult = FastJsonUtil.ajaxResult(false, "验证码错误！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);*/
            request.setAttribute("msg", "验证码错误！");
            return "userRegister";
        }

        if(StringUtils.isEmpty(userMd.getEmail())){
            userMd.setEmail(null);
        }

        try {
            // 上传成功的图片，文件默认在tomcat的临时目录 中
            File picture = userMd.getPicture();
            // 上传文件的原始名称
            String pictureFileName = userMd.getPictureFileName();
            // 上传文件的类型
            String pictureContentType = userMd.getPictureContentType();

            if (picture != null && pictureFileName != null && !pictureFileName.equals("")) {
                // 服务器图片存储路径
                String filePath = "D:\\develop\\upload\\";
                // 扩展名，从原始名称中截取
                String fileName_extension = pictureFileName.substring(pictureFileName.lastIndexOf("."));

                // 为了保证服务器上图片目录中图片名称不重复将每个上传的图片重定义一个名称
                // 新文件名称
                String fileNameNew = UUID.randomUUID().toString() + fileName_extension;
                // 定义一个File
                File fileNew = new File(filePath + fileNameNew);

                // 将tomcat下临时目录中的文件picture拷贝或移动到fileNew
                // 使用拷贝，拷贝完成tomcat下临时目录中的文件自动删除了
                FileUtils.copyFile(picture, fileNew);

                // 在数据库中保存图片路径
                userMd.setUserPicture(fileNameNew);
            }
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("msg", "头像上传失败！");
            return "userRegister";
        }

        //用于校验邮箱是否重复
        List<TUser> userList = userService.findUser();
        if (StringUtils.isNotEmpty(userMd.getEmail())) {
            for (TUser user : userList) {

                //先判断数据库中的用户邮箱是否为空，为空就判断下一个用户，不为空再校验
                if (StringUtils.isNotEmpty(user.getEmail())) {
                    if (user.getEmail().equals(userMd.getEmail())) {
                        request.setAttribute("msg", "邮箱已经被注册！");
                        return "userRegister";
                    }
                }
            }
        }

        try {
            userService.insertUser(userMd);
        } catch (IllegalAccessException e) {

            e.printStackTrace();
            request.setAttribute("msg", "注册失败！用户名已被注册");
            return "userRegister";
        } catch (InvocationTargetException e) {

            e.printStackTrace();
            request.setAttribute("msg", "注册失败！");
            return "userRegister";
        } catch (RuntimeException e) {

            e.printStackTrace();
            request.setAttribute("msg", "注册失败!用户名已经被注册");
            return "userRegister";
        }

        request.setAttribute("msg", "恭喜您，注册成功！请登录");
        return "userRegister";
    }

    public String toUpdateAccount() {
        int userid = (int) ServletActionContext.getRequest().getSession().getAttribute("userid");
        TUser user = userService.getUserById(userid);
        ServletActionContext.getRequest().setAttribute("user", user);
        return "toUpdateAccount";
    }


    public void verifyAdmin() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        HttpSession session = request.getSession();

        /*String nowAdminName = (String) session.getAttribute("adminName");
        String nowAdminId = (String) session.getAttribute("adminId");*/


        boolean flag = false;
        //从session重获取验证码
        String vcode = (String) request.getSession().getAttribute("vcode");

        //判断用户名是否为空
        if (StringUtils.isEmpty(userMd.getUsername())) {
            // 向客户端返回提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "用户名不能为空！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }

        //判断密码是否为空
        if (StringUtils.isEmpty(userMd.getUserPassword())) {
            // 向客户端返回提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "密码不能为空！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }

        //判断验证码是否正确
        if (vcode.equalsIgnoreCase(userMd.getVerifyCode()) == false) {
            // 向客户返回提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "验证码错误！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }

        if (CommonUtil.getWordCount(userMd.getUsername()) > 32) {

            String ajaxResult = FastJsonUtil.ajaxResult(false, "用户名过长");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }

        if (CommonUtil.getWordCount(userMd.getUserPassword()) > 32) {

            String ajaxResult = FastJsonUtil.ajaxResult(false, "密码过长");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }


        //验证码正确后，核对用户名和密码
        ArrayList<TAdministrator> list = (ArrayList<TAdministrator>) adminService.findAdmin();

        for (TAdministrator admin : list) {
            if (admin.getAdminName().equals(userMd.getUsername()) && admin.getAdminPassword().equals(userMd.getUserPassword())) {
                // 向客户返回成功提示
                String ajaxResult = FastJsonUtil.ajaxResult(true, "登录成功！");
                // 输出json
                FastJsonUtil.write_json(response, ajaxResult);

                //将用户名id和用户名放入session域中

                session.setAttribute("adminName", admin.getAdminName());
                session.setAttribute("adminId", admin.getAdminId());
                //终止执行
                return;
            }

            //用于验证用户的用户名是否存在
            if (admin.getAdminName().equals(userMd.getUsername())) {
                flag = true;
            }
        }

        //如果用户名不存在，直接向前端进行提示
        if (flag == false) {
            // 向客户返回成功提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "登录失败");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }

       /* if(nowAdminId.equals(session.getAttribute("adminId")) && nowAdminName.equals(session.getAttribute("adminName"))){
            // 向客户返回成功提示
            String ajaxResult = FastJsonUtil.ajaxResult(false, "用户已经登录，登录失败！");
            // 输出json
            FastJsonUtil.write_json(response, ajaxResult);
            //终止执行
            return;
        }*/

        // 向客户返回成功提示
        String ajaxResult = FastJsonUtil.ajaxResult(false, "用户名或者密码错误！");
        // 输出json
        FastJsonUtil.write_json(response, ajaxResult);
    }


    public String modifyPasswordByEmail() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        String vCode = (String) session.getAttribute("vcode");

        if (!vCode.equalsIgnoreCase(userMd.getVerifyCode())) {
            request.setAttribute("msg", "验证码错误");
            return "modifyPasswordByEmailError";
        }

        try {
            TUser user = userService.getUserByEmail(userMd.getEmail());

            request.setAttribute("nowUser", user);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "邮箱不存在");
            return "modifyPasswordByEmailError";
        }


        try {
            //产生随机数
            String randomNumber = CommonUtil.random();
            session.setAttribute("randomNumber", randomNumber);

            /*CommonUtil.sendMail(userMd.getEmail(), "randomNumber");*/
            CommonUtil.sendMail(userMd.getEmail(), randomNumber);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "发送邮件失败");
        }


        return "modifyPasswordByEmail";
    }

    public String modifyPasswordByEmailNext() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        String randomNumber = (String) session.getAttribute("randomNumber");

        if (StringUtils.isEmpty(randomNumber)) {

            request.setAttribute("msg", "请先发送邮件");
            return "modifyPasswordByEmailError";
        }

        if (!randomNumber.equals(userMd.getRandomNumber())) {
            request.setAttribute("msg", "邮箱验证码错误");

            TUser user = userService.getUserById(userMd.getUserId());
            request.setAttribute("nowUser", user);
            return "modifyPasswordByEmail";
        }

        if (!userMd.getNewPassword().equals(userMd.getUserPassword())) {

            request.setAttribute("msg", "两次密码不一致");
            TUser user = userService.getUserById(userMd.getUserId());
            request.setAttribute("nowUser", user);
            return "modifyPasswordByEmail";
        }


        try {
            userService.updateUserPasswordById(userMd.getUserId(), userMd.getUserPassword());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "请重新发送邮件");
            return "modifyPasswordByEmailError";
        }

        request.setAttribute("msg", "恭喜您，成功修改密码！");
        return "modifyPasswordByEmailNext";
    }

    public String logout() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.invalidate();
        return "logoutSuccess";
    }

    public String accountManager() {
        return "accountManager";
    }
}
