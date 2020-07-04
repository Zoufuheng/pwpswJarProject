<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/5/7
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册页面</title>
    <link href="${pageContext.request.contextPath}/css/user_register.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/user_vol_register_js/user.register.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/user_vol_register_js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/user_vol_register_js/distpicker.data.js"></script>
    <script src="${pageContext.request.contextPath}/js/user_vol_register_js/distpicker.js"></script>
    <%--
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">--%>
</head>

<body>
<div class="rg_layout">
    <div class="rg_left">
        <p id="user_register">新用户注册</p>
    </div>
    <%--end左浮动结束--%>

    <div class="rg_center">
        <div class="rg_form"><%--定义表单--%>
            <form action="${pageContext.request.contextPath}/user/userRegister.action" method="post" id="form"
                  enctype="multipart/form-data">
                <table>
                    <tr>
                        <td class="td_left"><label for="username"><span class="requirement">*</span>用户名</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="username" id="username" placeholder="请输入用户名">
                            <span id="s_username" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label>头像</label></td>
                        <td colspan="3">
                            <div align="center">
                                <span>
                                    <span>上传您的头像</span>
                                    <input type="file" name="picture" id="picture" onchange="fileChange(this);">
                                </span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="userNickname"><span class="requirement">*</span>昵称</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="userNickname" id="userNickname" placeholder="请输入昵称">
                            <span id="s_userNickname" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="userPassword"><span class="requirement">*</span>密码</label></td>
                        <td class="td_right" colspan="3">
                            <input type="password" name="userPassword" id="userPassword" placeholder="密码长度大于等于6位">
                            <span id="s_userPassword" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="man">性别</label></td>
                        <td class="td_right" colspan="3">
                            <input id="man" type="radio" checked="checked" name="userGender" value="男"/><span
                                class="man">男</span><input
                                id="woman" type="radio" name="userGender" value="女"/><span>女</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="userAge"><span class="requirement">*</span>年龄</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="userAge" id="userAge" placeholder="请输入年龄">
                            <span id="s_userAge" class="error"></span>
                        </td>
                    </tr>
                    <tr data-toggle="distpicker">
                        <td class="td_left"><label>省份</label></td>
                        <td class="td_right "><select id="province1" name="userProvince"></select></td>
                        <td class="td_left"><label>城市</label></td>
                        <td class="td_right"><select id="city1" name="userCity"></select></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="userPhone"><span class="requirement">*</span>电话号码</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="userPhone" id="userPhone" placeholder="请输入电话号码">
                            <span id="s_userPhone" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="email">Email</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="email" id="email" placeholder="请输入邮箱">
                            <span id="s_email" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="check_code"><span class="requirement">*</span>验证码</label></td>
                        <td class="td_right" colspan="4">
                            <div class="code">
                                <input type="text" value="" placeholder="请输入验证码（不区分大小写）" id="check_code"
                                       name="verifyCode"
                                       class="input-val">
                                <%--<canvas id="canvas" width="100" height="30"></canvas>--%>
                                <a href="#" onclick="changeImage()"><img id="image" style="width: 115px;height: 40px"
                                                                         src="${pageContext.request.contextPath}/user/vcode.action"></a>
                            </div>
                        </td>

                    </tr>
                    <tr>
                        <td></td>
                        <td><font color="red" style="margin-left: 29px">${msg}</font></td>
                    </tr>
                    <tr>
                        <td class="btn_left" align="center" colspan="4">
                            <input type="submit" name="btn_register" value="注册"  style="cursor: pointer" id="btn_register" class="btn">
                            <input type="reset" name="btn_register" value="重置" style="cursor: pointer" id="btn_reset">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4"><s:fielderror/></td>
                    </tr>
                </table>
            </form>
        </div>
        <%--end定义表单--%>
    </div>
    <%--end中间浮动结束--%>

    <%--class="rg_right"--%>
    <div >
        <p id="have_account"><a href="${pageContext.request.contextPath}/index.jsp">回到主页</a> 已有账号<a
                href="${pageContext.request.contextPath}/jsps/login/login.jsp">立即登录</a></p>
    </div>
    <%--end右浮动结束--%>
</div>

<script>
    function changeImage() {
        var image = document.getElementById("image");
        image.src = "${pageContext.request.contextPath}/user/vcode.action?a=" + new Date().getTime();
    }

    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;

    function fileChange(target, id) {
        var fileSize = 0;
        var filetypes = [".jpg", ".png", ".jpeg", ".bmp", ".BMP", ".PNG", ".JPEG"];
        var filepath = target.value;
        var filemaxsize = 1024 * 2;//2M
        if (filepath) {
            var isnext = false;
            var fileend = filepath.substring(filepath.indexOf("."));
            if (filetypes && filetypes.length > 0) {
                for (var i = 0; i < filetypes.length; i++) {
                    if (filetypes[i] == fileend) {
                        isnext = true;
                        break;
                    }
                }
            }
            if (!isnext) {
                alert("不接受此图片类型！");
                target.value = "";
                return false;
            }
        } else {
            return false;
        }
        if (isIE && !target.files) {
            var filePath = target.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            if (!fileSystem.FileExists(filePath)) {
                alert("图片不存在，请重新添加！");
                return false;
            }
            var file = fileSystem.GetFile(filePath);
            fileSize = file.Size;
        } else {
            fileSize = target.files[0].size;
        }

        var size = fileSize / 1024;
        if (size > filemaxsize) {
            alert("附件大小不能大于" + filemaxsize / 1024 + "M！");
            target.value = "";
            return false;
        }
    }

</script>
</body>
</html>
