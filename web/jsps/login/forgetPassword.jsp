<%--
  Created by IntelliJ IDEA.
  User: 75164
  Date: 2020/6/3
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>

    <style>

        .td {
            text-align: right;
            font-family: '微软雅黑';
            width: 140px;
            height: 25px;
            /* word-wrap: 'nowrap';*/
            font-size: 18px;
            color: #000000;
        }

        .input {
            /* display: block;*/
            line-height: 1.42857143;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 317px;
            height: 32px;
            font-family: '微软雅黑';
            font-size: 18px;
            color: #000000;

        }

        font {
            font-family: '微软雅黑';
            font-size: 18px;
        }


        #ss {
            width: 100%;
            background-color: #f45438;
            color: white;
            padding: 10px 10px;
            /*margin-left: 34px;*/
            /*margin-right: 9px;*/
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<jsp:include page="/jsps/index/picture_nav.jsp"/>

<div class="col-12"
     style="height: 100px; display: flex; flex-flow: column nowrap; border-width: 0px; position: relative;"
     candrop="ecp-flex-vertical" id="ecpflexvertical_2271"
     xdeer-flex-vertical="ecp-flex-vertical_f8018871c625a6daecd0654e78af5b8f"
     data-option="{'id':'ecpflexvertical_2271','height':'200px','paddingLeft':'0px','paddingRight':'0px','paddingTop':'0px','paddingBottom':'0px','marginleft':'0px','marginRight':'0px','marginBottom':'0px','marginTop':'0px','border-left-width':'0px','border-right-width':'0px','border-top-width':'0px','border-bottom-width':'0px'}"
     dom-data-type="ecp-flex-vertical" dom-data-version="13">
    <div candrop="ecp-flex-col" style="display: flex; width: 100%; flex: 1 1 auto; border-width: 0px;"
         xdeer-flex-col="7af1373324ad87e7696f4052f47b4e26"
         data-option="{'flex-basis':'auto','flex-grow':'1','flex-shrink':'1','width':'100%','paddingLeft':'0px','paddingRight':'0px','paddingTop':'0px','paddingBottom':'0px','marginleft':'0px','marginRight':'0px','marginBottom':'0px','marginTop':'0px','border-left-width':'0px','border-right-width':'0px','border-top-width':'0px','border-bottom-width':'0px','border-width':'0px'}"></div>
</div>
<h2 style="align-content: center">温馨提示：为了保证邮件能够发送成功，请先将sdauuse@163.com设置为自己邮箱的白名单</h2>
<br>

<form id="postForm" action="${pageContext.request.contextPath}/user/modifyPasswordByEmail.action" method="post">

    <table style="margin: auto;border-collapse:separate; border-spacing:0px 11px;">

        <tr>
            <td class="td"></td>
            <td><font color="red">${msg}</font></td>
        </tr>
        <tr>
            <td class="td">邮箱：</td>
            <td class="td"><input class="input" type="text" required name="email" placeholder="请输入您的邮箱"></td>
        </tr>
        <tr>
            <td class="td">验证码：</td>
            <td class="td">
                <input class="input" type="text" required name="verifyCode"
                       style="width: 170px;height: 32px;float:left">
                <a href="#" onclick="changeImage()"><img id="image" width="115" height="32"
                                                         src="${pageContext.request.contextPath}/user/vcode.action"></a>
            </td>

        </tr>

        <tr>
            <td class="td"></td>
            <td class="td"><input id="ss" type="submit" value="发送，并进入下一步"></td>
        </tr>
    </table>

</form>


<div class="col-12"
     style="height: 230px; display: flex; flex-flow: column nowrap; border-width: 0px; position: relative;"
     candrop="ecp-flex-vertical" id="ecpflexvertical_2271"
     xdeer-flex-vertical="ecp-flex-vertical_f8018871c625a6daecd0654e78af5b8f"
     data-option="{'id':'ecpflexvertical_2271','height':'200px','paddingLeft':'0px','paddingRight':'0px','paddingTop':'0px','paddingBottom':'0px','marginleft':'0px','marginRight':'0px','marginBottom':'0px','marginTop':'0px','border-left-width':'0px','border-right-width':'0px','border-top-width':'0px','border-bottom-width':'0px'}"
     dom-data-type="ecp-flex-vertical" dom-data-version="13">
    <div candrop="ecp-flex-col" style="display: flex; width: 100%; flex: 1 1 auto; border-width: 0px;"
         xdeer-flex-col="7af1373324ad87e7696f4052f47b4e26"
         data-option="{'flex-basis':'auto','flex-grow':'1','flex-shrink':'1','width':'100%','paddingLeft':'0px','paddingRight':'0px','paddingTop':'0px','paddingBottom':'0px','marginleft':'0px','marginRight':'0px','marginBottom':'0px','marginTop':'0px','border-left-width':'0px','border-right-width':'0px','border-top-width':'0px','border-bottom-width':'0px','border-width':'0px'}"></div>
</div>

<jsp:include page="/jsps/index/footer.jsp"/>
</body>
<script>
    function changeImage() {
        var image = document.getElementById("image");
        image.src = "${pageContext.request.contextPath}/user/vcode.action?a=" + new Date().getTime();
    }
</script>
</html>
