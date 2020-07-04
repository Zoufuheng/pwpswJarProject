<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>通告内容</title>
    <style type="text/css">
        #notice{
            /*background-color: cornsilk;*/
            width: 80%;
            margin: 60px auto;
        }
        #headline h2{
            text-align: center;
        }
        #notice_message #message_left{
            float: left;
            margin-left: 10px;
        }
        #notice_message #message_right{
            float: right;
            margin-right: 30px;
        }
        #notice_message{
            font-size: 13px;
            font-family: Arial;
        }
        #notice_main{
            font-size: 15px;
            margin-top: 20px;
        }
        .td1{
             background-color: gainsboro;
         }
        .td2{
            font-size: 13px;
            padding-top: 55px;
        }
        .a1{
            text-decoration: none;
        }
        #notice  a:hover{
            color: red;
        }
        #notice_index td a:link,a:visited,a:active{
            color: #2e6da4;
        }
        body{
            margin: 0px;
        }
        #notice table{
            width: 80%;
            margin: 0px auto;
        }
    </style>
</head>
<body>
    <jsp:include page="/jsps/index/picture_nav.jsp"/><%--顶部图片和导航栏--%>
    <div id="notice">
        <table>
            <tr>
                <td>
                    <div id="headline"><%--通告标题--%>
                        <h2>${notice.ntsHeadLine}</h2>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="td1">
                    <div id="notice_message"><%--通告信息--%>
                        <span id="message_left">发布者：${notice.administrator.adminName}</span>
                        <span id="message_right">发布日期：${notice.ntsDate}</span>
                    </div>
                </td>

            </tr>
            <tr>
                <td>
                    <div id="notice_main"><%--通告正文--%>
                        ${notice.ntsMain}
                    </div>
                </td>
            </tr>
                <tr>
                    <div id="notice_index">
                        <td class="td2">上一篇:<a href="${pageContext.request.contextPath}/notice/Lastnotice.action?ntsId=${notice.ntsId}&ntsDate=${notice.ntsDate}&page=${page}" class="a1">上一条通告</a><br><br/>

                        下一篇:<a href="${pageContext.request.contextPath}/notice/Nextnotice.action?ntsId=${notice.ntsId}&ntsDate=${notice.ntsDate}&page=${page}" class="a1">下一条通告</a><br><br/>

                        <a href="${pageContext.request.contextPath}/notice/noticeList.action?page=${page}&rows=10" class="a1">返回通告列表</a></td>
                    </div>
                </tr>

        </table>
    </div>
</body>
</html>
