<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息修改页面</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">


    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    <link href="${pageContext.request.contextPath}/css/accountManager/accountManager.css" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/accountManager/accountManager.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/user_vol_register_js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/user_vol_register_js/distpicker.data.js"></script>
    <script src="${pageContext.request.contextPath}/js/user_vol_register_js/distpicker.js"></script>

    <style type="text/css">
        #userNickname {
            width: 300px;
            height: 50px;
            border: 1px solid gray;
            /*设置边框圆角*/
            border-radius: 5px;
            padding-left: 10px;
        }

        .td2 {
            padding-left: -10px;
            padding-bottom: 15px;
            text-align: left;
        }

        img {
            width: 60px;
            height: 60px;
            margin-left: 60px;
        }

        .rg_form table tr td {
            width: 250px;
        }
    </style>
    <script>
        function updateSuccess() {
            alert("保存成功");
        }
    </script>
</head>
<body>

<jsp:include page="/jsps/index/picture_nav.jsp"/>


<div class="rg_layout">
    <div class="rg_left">
        <p id="user_register">个人信息修改</p>
    </div>
    <%--end左浮动结束--%>

    <div class="rg_center">
        <div class="rg_form"><%--定义表单--%>
            <form action="${pageContext.request.contextPath}/user/submitpicture.action" method="post" id="form"
                  enctype="multipart/form-data">
                <table>
                    <tr>
                        <td colspan="1" class="td_left">
                            <c:if test="${empty user.userPicture}"><img src="${pageContext.request.contextPath}/imgs/defaultUser.png" alt=""></c:if>
                            <c:if test="${not empty user.userPicture}"><img src="/upload/${user.userPicture}"/></c:if>
                        </td>
                        <td class="td2" colspan="2">
                            <div align="center">
                                <span class="btn btn-success fileinput-button">
                                    <span>选择新头像</span>
                                    <input type="file" name="picture">
                                </span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="username">用户名</label></td>
                        <td class="td_right" colspan="2">
                            <input type="text" name="username" id="username" value="${user.username}">
                        </td>
                        <td>
                            <span id="s_username" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="userNickname">昵称</label></td>
                        <td class="td_right" colspan="2">
                            <input type="text" name="userNickname" id="userNickname" value="${user.userNickname}">
                            <%--                            <span id="s_userNickname" class="error" style="width: 50px;"></span>--%>
                        </td>
                        <td>
                            <span id="s_userNickname" class="error" style="width: 50px;"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="man">性别</label></td>
                        <td class="td_right" colspan="3">
                            <c:choose>
                                <c:when test="${user.userGender=='男'}">
                                    <input id="man" type="radio" checked="checked" name="userGender" value="男"/><span
                                        class="man">男</span><input
                                        id="woman" type="radio" name="userGender" value="女"/><span>女</span>
                                </c:when>
                                <c:otherwise>
                                    <input id="man" type="radio" name="userGender" value="男"/><span
                                        class="man">男</span><input
                                        id="woman" type="radio" checked="checked" name="userGender"
                                        value="女"/><span>女</span>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="userAge">年龄</label></td>
                        <td class="td_right" colspan="2">
                            <input type="text" name="userAge" id="userAge" value="${user.userAge}">
                        </td>
                        <td>
                            <span id="s_userAge" class="error"></span>
                        </td>
                    </tr>
                    <tr data-toggle="distpicker">
                        <td class="td_left"><label>省份</label></td>
                        <td class="td_right "><select id="province1" name="userProvince"></select></td>
                        <td class="td_left" style="width: 80px"><label>城市</label></td>
                        <td class="td_right"><select id="city1" name="userCity"></select></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="userPhone">电话号码</label></td>
                        <td class="td_right" colspan="2">
                            <input type="text" name="userPhone" id="userPhone" value="${user.userPhone}">
                        </td>
                        <td>
                            <span id="s_userPhone" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="email">Email</label></td>
                        <td class="td_right" colspan="2">
                            <input type="text" name="email" id="email" value="${user.email}">
                        </td>
                        <td>
                            <span id="s_email" class="error"></span>
                        </td>
                    </tr>

                    <tr>
                        <td class="btn_left" align="center" colspan="4" rolspan="2">
                            <input type="submit" name="btn_register" value="保存" id="btn_register" class="btn">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" rolspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4"><s:fielderror/></td>
                    </tr>
                    <tr>
                        <td colspan="4" rolspan="2">
                            <c:if test="${message eq '保存成功'}">
                                <script>
                                    updateSuccess();
                                </script>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="hidden" name="userId" value="${user.userId}"></td>
                        <td><input type="hidden" name="userPicture" value="${user.userPicture}"></td>
                    </tr>
                </table>
            </form>
        </div>
        <%--end定义表单--%>
    </div>
</div>

</body>
</html>
