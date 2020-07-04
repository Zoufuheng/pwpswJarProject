<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的账号</title>
    <style type="text/css">
        #myAccount #line #div1{
            color: white;
            margin-left: 25px;
            padding-top: 15px;
            width: auto;
            height: auto;
            float: left;
        }
        #myAccount #line{
            width: 80%;
            height: 50px;
            margin: 0px auto;
            padding: 0px;
            background-color: #337ab7;
        }
        #myAccount #accountFunction{
            width: 500px;
            margin: 50px auto;
            font-size: 23px;
        }
        #myAccount #accountFunction a{
            text-decoration: none;
        }
        #myAccount #accountFunction a:link,a:visited,a:active{
            color: black;
        }
        #myAccount #accountFunction a:hover{
            color: cornflowerblue;
        }
        body{
            margin: 0px;
            padding: 0px;
        }
        #myAccount{
            margin-top: 90px;
        }
    </style>
</head>
<body>
<jsp:include page="/jsps/index/picture_nav.jsp"/>

<div id="myAccount">
    <div id="line">
        <div id="div1">我的账号</div>
    </div>
    <div id="accountFunction">
        <a href="${pageContext.request.contextPath}/post/Posting.action">发布帖子</a>&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/post/findPostListByUserId.action">我的帖子</a>&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/user/toUpdateAccount.action">个人信息修改</a>&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/jsps/message/message.jsp">留言板</a>
    </div>
</div>
</body>
</html>
