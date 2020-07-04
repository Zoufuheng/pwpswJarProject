<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>顶部图片</title>
    <style type="text/css">
        #picture {
            width: 100%;
            margin: 0px auto;
            height: 150px;
            background-color: white;
        }

        #header_picture img {
            width: 110px;
            height: 110px;
            float: left;
        }

        #header_picture h1 {
            float: left;
            padding-top: 10px;
            padding-left: 10px;
            color: #E92E1D;
        }

        #header_picture {
            padding-left: 130px;
            padding-top: 20px;
        }

        #login_register {
            float: right;
            margin-right: 190px;
            margin-top: 7px;
        }

        #login_register a {
            font-size: 15px;
            font-family: Arial;
        }

        #login_register a:link, a:visited, a:hover, a:active {
            text-decoration: none;
        }

        #login_register a:link, a:visited, a:active {
            color: black;
        }

        #login_register a:hover {
            color: cornflowerblue;
        }

        body {
            margin: 0px;
            padding: 0px;
        }

        #login_register img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
        }
    </style>
</head>
<body>
<div id="picture">
    <div id="header_picture">
        <img src="${pageContext.request.contextPath}/imgs/index/header_picture.jpg"/>
        <h1><strong>公益寻人网</strong></h1>
    </div>

    <div id="login_register">
        <%--<c:choose>
            <c:when test="${empty userid}">
                <a href="${pageContext.request.contextPath}/jsps/login/login.jsp">登陆</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jsps/userRegister.jsp">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;
                &lt;%&ndash;<a href="${pageContext.request.contextPath}/jsps/login/login.jsp"><img
                        src="${pageContext.request.contextPath}/imgs/defaultUser.png"></a>&ndash;%&gt;
            </c:when>

            <c:otherwise>
                你好！${usernickname}&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/jsps/myAccount/myAccount.jsp"><img src="/upload/${userpicture}"></a>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/logout.action">注销</a>
            </c:otherwise>
        </c:choose>--%>

        <c:choose>
            <c:when test="${empty userid}">
                <a href="${pageContext.request.contextPath}/jsps/login/login.jsp">登陆</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/jsps/userRegister.jsp">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${not empty userpicture}">
                你好！${usernickname}&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/jsps/myAccount/myAccount.jsp"><img src="/upload/${userpicture}"></a>&nbsp;&nbsp;&nbsp;
            </c:when>

            <c:otherwise>
                你好！${usernickname}&nbsp;<a href="${pageContext.request.contextPath}/jsps/myAccount/myAccount.jsp"><img
                src="${pageContext.request.contextPath}/imgs/defaultUser.png">
                </a>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${not empty userid}">
                <a href="${pageContext.request.contextPath}/user/logout.action">注销</a>
            </c:when>
        </c:choose>

    </div>
</div>
</body>
</html>
