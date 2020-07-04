package com.pwpew.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pwpew.entity.TAdministrator;
import com.pwpew.entity.TUser;
import com.pwpew.modeldriven.AdminMd;
import com.pwpew.service.AdminService;
import com.pwpew.utils.FastJsonUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/13 - 12:57
 * @commet:管理员使用的action
 */
@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport implements ModelDriven<AdminMd> {

    @Autowired
    private AdminService adminService;

    private AdminMd adminMd = new AdminMd();

    @Override
    public AdminMd getModel() {
        return adminMd;
    }


    public void findAdminList() {

        List<TAdministrator> list = adminService.findAdmin();

        //转换成json
        String jsonString = FastJsonUtil.toJSONString(list);
        HttpServletResponse response = ServletActionContext.getResponse();
        FastJsonUtil.write_json(response, jsonString);
    }


    public String quit() {

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("adminName");
        session.removeAttribute("adminId");

        return SUCCESS;
    }

    public void updatePassword() {
        HttpServletResponse response = ServletActionContext.getResponse();

        try {
            TAdministrator admin = adminService.getAdminById(adminMd.getAdminId());
            if(adminMd.getAdminPassword()!=null){
                admin.setAdminPassword(adminMd.getAdminPassword());
            }else{
                String ajaxResult = FastJsonUtil.ajaxResult(false, "修改失败");
                FastJsonUtil.write_json(response, ajaxResult);
                return;
            }

            adminService.updateAdmin(admin);
        }catch (Exception e){
            e.printStackTrace();
            String ajaxResult = FastJsonUtil.ajaxResult(false, "修改失败");
            FastJsonUtil.write_json(response, ajaxResult);
            return;
        }

        String ajaxResult = FastJsonUtil.ajaxResult(true, "修改成功");
        FastJsonUtil.write_json(response, ajaxResult);
        return;
    }

}
