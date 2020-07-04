package com.pwpew.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pwpew.entity.TDatadict;
import com.pwpew.modeldriven.DatadictMd;
import com.pwpew.service.DatadictService;
import com.pwpew.utils.FastJsonUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/21 - 17:35
 * @commet:
 */
@Controller("datadictAction")
@Scope("prototype")
public class DatadictAction extends ActionSupport implements ModelDriven<DatadictMd> {

    @Autowired
    private DatadictService datadictService;

    private DatadictMd datadictMd = new DatadictMd();

    @Override
    public DatadictMd getModel() {
        return datadictMd;
    }

    //通过数据字典类别代码查询数据字典
    public void findByDictTypeCode(){

        String dictTypeCode = datadictMd.getDictTypeCode();
        List<TDatadict> list = datadictService.findByDictTypeCode(dictTypeCode);

        //转换成json对象
        HttpServletResponse response = ServletActionContext.getResponse();
        String jsonString = FastJsonUtil.toJSONString(list);
        FastJsonUtil.write_json(response, jsonString);
    }
}
