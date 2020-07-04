<%@ page import="com.pwpew.service.NoticeService" %>
<%@ page import="org.aspectj.bridge.MessageWriter" %>
<%@ page import="com.pwpew.service.impl.NoticeServiceImpl" %>
<%@ page import="com.pwpew.entity.TNotice" %>
<%@ page import="com.pwpew.dao.impl.NoticeDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>搜索栏</title>
    <style type="text/css">
        #research #research1{
            float: left;
            padding-left: 180px;
        }
        #research #research1 a:hover{
            color: cornflowerblue;
        }
        #research #research1 img{
            width: 45px;
            height: 27px;
        }
        #research #research2{
            float: right;
            padding-right: 180px;
        }
        #research{
            padding-top: 20px;
            padding-bottom: 50px;
        }
        .input1{
            width: 285px;
            height: 35px;
        }
    </style>
</head>
<body>
<s:action name="latestNotice" namespace="/notice"/>
<div id="research">
    <span id="research1"><font size="3">最新通告:</font>&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/imgs/index/research_picture.jpg"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/notice/noticeMain.action?ntsId=${latestNotice.ntsId}">${latestNotice.ntsHeadLine}</a></span>
    <span id="research2">
        <form action="${pageContext.request.contextPath}/post/searchPost.action?page=1&rows=10" method="post">
          <input type="text" class="input1" name="keyword" placeholder="请输入查找的姓名或相关信息">&nbsp;<button type="submit" class="btn btn-primary">搜索</button>
        </form>
      </span>
</div>
</body>
</html>
