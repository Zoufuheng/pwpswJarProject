<%@ page import="com.pwpew.entity.TMessage" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 96989
  Date: 2020/6/6
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        a{
            text-decoration: none;
        }
    </style>
</head>
<body style="padding: 0;">
<jsp:include page="/jsps/index/picture_nav.jsp"/><%--顶部图片和导航栏集合--%>

<div align="center" style="width: 60%; margin: 50px auto ;text-align: center">
    <h3>历史留言</h3>
    <div style="font-size: 15px;font-weight: 400;line-height: 2em;text-align: left;border: 1px solid #778899;border-radius: 10px; padding: 30px;">
        <c:forEach items="${messageList}" var="i">
            <c:if test="${not empty i.msgMain}">
                时间：${i.msgDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${i.user.userNickname}留言：${i.msgMain}<br>
            </c:if>
            <c:if test="${not empty i.msgResMain}">
                时间：${i.msgResTime}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理员回复：${i.msgResMain}<br>
            </c:if>
        </c:forEach>
    </div>
    <h4 align="right" style="text-align: right"><a href="${pageContext.request.contextPath}/jsps/message/message.jsp">返回</a></h4>
</div>
<jsp:include page="/jsps/index/footer.jsp"/><%--页脚--%>
</body>
</html>
