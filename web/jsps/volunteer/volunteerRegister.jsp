<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/5/10
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>志愿者注册页面</title>
    <link href="${pageContext.request.contextPath}/css/volunteer_register.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/user_vol_register_js/volunteer.register.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/user_vol_register_js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/user_vol_register_js/distpicker.data.js"></script>
    <script src="${pageContext.request.contextPath}/js/user_vol_register_js/distpicker.js"></script>

</head>

<body>
<div class="vol_layout">
    <div class="vol_left">
        <p id="vol_register">志愿者注册</p>
    </div>
    <%--end左浮动结束--%>

    <div class="vol_center">
        <div class="vol_form"><%--定义表单--%>
            <form method="post" id="form">
                <table>
                    <tr>
                        <td class="td_left"><label for="volname"><span class="requirement">*</span>志愿者姓名</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="volname" id="volname" placeholder="请输入姓名">
                            <span id="s_volname" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="volIdCard"><span class="requirement">*</span>身份证号码</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="volIdCard" id="volIdCard" placeholder="请输入身份证号">
                            <span id="s_volIdCard" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="volPhone"><span class="requirement">*</span>手机号码</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="volPhone" id="volPhone" placeholder="请输入手机号码">
                            <span id="s_volPhone" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="man">性别</label></td>
                        <td class="td_right" colspan="3">
                            <input id="man" type="radio" value="男" checked="checked" name="volSex"/><span
                                class="man">男</span><input
                                id="woman" type="radio" name="volSex" value="女"/><span>女</span>
                        </td>
                    </tr>
                    <tr data-toggle="distpicker">
                        <td class="td_left"><label>省份</label></td>
                        <td class="td_right "><select id="province1" name="volProvince"></select></td>
                        <td class="td_left"><label>城市</label></td>
                        <td class="td_right"><select id="city1" name="volCity"></select></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="voldetailAddr"><span class="requirement">*</span>详细地址</label>
                        </td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="voldetailAddr" id="voldetailAddr" placeholder="请输入详细地址">
                            <span id="s_voldetailAddr" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="volProfession">职业</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="volProfession" id="volProfession" placeholder="请输职业">
                            <span id="s_volProfession" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="volEmail">电子邮件</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="volEmail" id="volEmail" placeholder="请输入电子邮件">
                            <span id="s_volEmail" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="volQQ"><span class="requirement">*</span>QQ号</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="volQq" id="volQQ" placeholder="请输入QQ号">
                            <span id="s_volQQ" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="check_code"><span class="requirement">*</span>验证码</label></td>
                        <td class="td_right" colspan="4">
                            <div class="code">
                                <input type="text" name="verifyCode" value="" placeholder="请输入验证码（不区分大小写）"
                                       id="check_code"
                                       class="input-val">

                                <a href="#" onclick="changeImage()"><img id="image" style="width: 115px;height: 40px"
                                                                         src="${pageContext.request.contextPath}/user/vcode.action"></a>
                                <%--<canvas id="canvas" width="100" height="30"></canvas>--%>
                            </div>
                        </td>

                    </tr>
                    <tr>
                        <td></td>
                        <td><font style="margin-left: 29px" color="red">${msg}</font></td>
                    </tr>
                    <tr>
                        <td class="btn_left" align="center" colspan="4">
                            <input type="button" name="btn_vol_register" value="注册" id="btn_vol_register" class="btn"
                                   onclick="register()">
                            <input type="reset" name="btn_register" value="重置" id="btn_reset">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4"><s:fielderror /></td>
                    </tr>
                </table>
            </form>
        </div>
        <%--end定义表单--%>
    </div>
    <%--end中间浮动结束--%>

    <div class="vol_right">

        <p id="have_account"><a href="${pageContext.request.contextPath}/index.jsp">返回主页</a></p>
    </div>
    <%--end右浮动结束--%>
</div>

<script>
    function changeImage() {
        var image = document.getElementById("image");
        image.src = "${pageContext.request.contextPath}/user/vcode.action?a=" + new Date().getTime();
    }

    function register() {
        $.ajax({
            cache: false,
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/volunteer/volunteerRegister.action", //把表单数据发送到后端
            data: $('#form').serialize(),
            //要发送的是ajaxFrm表单中的数据
            /*  error: function (request) {
                  alert("发送请求失败！");
              }*/
            success: function (data) {
                //var result = JSON.stringify(data);
                //提示操作结果
                alert(data.message);

                if (data.success == true) {
                    window.location.replace("${pageContext.request.contextPath}/jsps/volunteer/volunteerInfo.jsp");
                }
            }
        });
    }
</script>
</body>
</html>
