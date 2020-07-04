<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        #footer{
            width: 100%;
            height: 120px;
            text-align: center;
            background-color: #dcdcdc;
            font-family: Arial;
            margin:0px;
            padding: 0px;
        }
        #footer ul{
            margin-left: auto;
        }
        #footer ul li{
            display: inline-block;
            height: 30px;
            width: 260px;
            font-size: 17px;
            padding-top: 27px;
            text-align: center;
            list-style: none;
        }
        #footer ul li a:link,a:visited,a:hover,a:active{
            text-decoration: none;
        }
        #footer ul li a:link,a:visited,a:active{
            color: black;
        }
        #footer ul li a:hover{
            color: cornflowerblue;
        }
        #logo img{
            width: 70px;
            height: 70px;
        }
        #logo{
            float: left;
            margin-left: 90px;
            margin-top: 18px;
        }
        #footer p{
            margin-top: 30px;
            margin-left: auto;
            margin-right: auto;
        }
        div,ul,li{
            margin: 0px;
            padding: 0px;
        }
    </style>
</head>
<body>
<div id="footer">
    <span id="logo"><img src="${pageContext.request.contextPath}/imgs/index/logo.jpg"></span>
    <ul>
        <li><a href="${pageContext.request.contextPath}/jsps/index/knowUs.jsp">关于我们</a></li>
        <li><a href="${pageContext.request.contextPath}/jsps/posts/posting.jsp">寻亲登记</a></li>
        <li><a href="${pageContext.request.contextPath}/jsps/myAccount/myAccount.jsp">用户中心</a></li>
        <li><a href="#">联系我们</a></li>
    </ul>
    <p>版权所有&copy;2019-2020  公益寻人网  .All Rights Reserved</p>
</div>
</body>
</html>
