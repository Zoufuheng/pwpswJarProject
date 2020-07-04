<%--
  Created by IntelliJ IDEA.
  User: 75164
  Date: 2020/5/8
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录页面</title>

    <link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/login/login.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/login/login2.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bac/ui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bac/ui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/bac/ui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/bac/ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/bac/ui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/bac/static/uimaker/js/jquery.min.js"></script>
    <%-- <meta http-equiv="Access-Control-Allow-Origin" content="*">--%>
</head>

<body>
<div class="login-hd">
    <div class="left-bg"></div>
    <div class="right-bg"></div>
    <span class="logo"></span>
    <span class="split"></span>
    <span class="sys-name">公益寻人系统登录页面</span>
</div>

<div class="login-bd">
    <div class="lg-zone">
        <div class="lg-box">
            <div class="lg-label">用户登录</div>
            <font style="margin-left: 34px;" color="red">${msg}</font>
            <br>
            <!--用jquery实现页面提交-->
            <form id="loginForm" method="post">
                <div class="lg-username input-item clearfix">
                    <i><img style="padding:8px;height: 22px;width: 22px;background-color: #FFFFFF"
                            src="${pageContext.request.contextPath}/imgs/login/user.png"></i>
                    <input type="text" placeholder="账号" id="username" name="username" value="${username}">
                </div>
                <div class="lg-password input-item clearfix">
                    <i><img style="padding:5px;height: 30px;width: 30px;background-color: #FFFFFF"
                            src="${pageContext.request.contextPath}/imgs/login/password.png"></i>
                    <input type="password" placeholder="请输入密码" id="userPassword" name="userPassword">
                </div>
                <div class="lg-check clearfix">
                    <div class="input-item">
                        <i><img style="padding:5px;height: 30px;width: 30px;background-color: #FFFFFF"
                                src="${pageContext.request.contextPath}/imgs/login/verifyCode.png"></i>
                        <input type="text" id="verifyCode" name="verifyCode" placeholder="验证码">
                    </div>
                    <a href="#" onclick="changeImage()"><img id="image" style="width: 115px;height: 40px"
                                                             src="${pageContext.request.contextPath}/user/vcode.action"></a>
                </div>

                <div class="tips clearfix">
                    <a href="${pageContext.request.contextPath}/index.jsp" id="change" style="cursor:pointer;">返回首页</a>
                    <a id="areg" href="${pageContext.request.contextPath}/jsps/userRegister.jsp" class="">立即注册</a>
                    <a id="afor" href="${pageContext.request.contextPath}/jsps/login/forgetPassword.jsp"
                       class="">忘记密码？</a>
                </div>
                <%-- <button id="btn" type="button" onclick="doFind()">保存</button>--%>

                <div class="enter">
                    <input id="btnuser" type="button" onclick="doFind()" value="用户登录">
                    <input id="btnvol" type="button" onclick="doFind2()" value="管理员登录">
                    <%--<a href="" id="usera" onclick="doFind()" class="purchaser">用户登录</a>
                    <a href="" onclick="doFind()" class="supplier">志愿者登录</a>--%>
                </div>
            </form>
        </div>
    </div>
    <div class="lg-poster"></div>
</div>


<div class="login-ft">
    <div class="about-us">
        <a href="${pageContext.request.contextPath}/jsps/index/knowUs.jsp">关于我们</a>
        <a href="${pageContext.request.contextPath}/jsps/posts/posting.jsp">寻亲登记</a>
        <a href="${pageContext.request.contextPath}/index.jsp">回到首页</a>
        <a href="javascript:">联系方式</a>
    </div>
    <div class="address">地址：广东省珠海市唐家湾金凤路6号&nbsp;邮编：519088&nbsp;&nbsp;Copyright&nbsp;©&nbsp;2020&nbsp;</div>
    <div class="other-info">建议使用IE8及以上版本浏览器&nbsp;&nbsp;E-mail：zhbit@zhbit.com</div>
</div>


</body>
</html>

<script type="text/javascript">

    //用户登录ajax请求
    function doFind() {
        $.ajax({
            cache: false,
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/user/verifyUser.action", //把表单数据发送到ajax.jsp
            data: $('#loginForm').serialize(),
            //要发送的是ajaxFrm表单中的数据
            /*  error: function (request) {
                  alert("发送请求失败！");
              }*/
            success: function (data) {
                //var result = JSON.stringify(data);
                //提示操作结果
                if (data.success == false) {
                    alert(data.message);
                }

                //如果后台验证成功，则跳转
                if (data.success == true) {
                    //1.利用http的重定向来跳转
                    //alert(data.message);
                    window.location.replace("${pageContext.request.contextPath}/index.jsp");
                    //2.使用href来跳转
                    //window.location.href = "${pageContext.request.contextPath}/jsps/success.jsp";
                }
            }
        });
    }

    //管理员登录
    function doFind2() {
        $.ajax({
            cache: false,
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/user/verifyAdmin.action", //把表单数据发送到后端
            data: $('#loginForm').serialize(),
            //要发送的是ajaxFrm表单中的数据
            /*  error: function (request) {
                  alert("发送请求失败！");
              }*/
            success: function (data) {
                //var result = JSON.stringify(data);
                //提示操作结果
                if (data.success == false) {
                    alert(data.message);
                }

                //如果后台验证成功，则跳转
                if (data.success == true) {
                    //1.利用http的重定向来跳转
                    window.location.replace("${pageContext.request.contextPath}/bac/index.jsp");
                    //2.使用href来跳转
                    //window.location.href = "http://www.baidu.com";
                }
            }
        });
    }

    function changeImage() {
        var image = document.getElementById("image");
        image.src = "${pageContext.request.contextPath}/user/vcode.action?a=" + new Date().getTime();
    }
</script>