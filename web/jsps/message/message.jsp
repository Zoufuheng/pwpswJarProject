<%--
  Created by IntelliJ IDEA.
  User: 96989
  Date: 2020/5/29
  Time: 20:12
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
    <script>
        function CurrentTime() {
            var now = new Date();
            var year = now.getFullYear();       //年
            var month = now.getMonth() + 1;     //月
            var day = now.getDate();            //日
            var hh = now.getHours();            //时
            var mm = now.getMinutes();          //分
            var ss = now.getSeconds();           //秒
            var clock = year + "-";
            if(month < 10)
                clock += "0";
            clock += month + "-";
            if(day < 10)
                clock += "0";
            clock += day + " ";
            if(hh < 10)
                clock += "0";
            clock += hh + ":";
            if (mm < 10) clock += '0';
            clock += mm + ":";
            if (ss < 10) clock += '0';
            clock += ss;

            document.getElementById("msgDate").value=clock;

            document.getElementById("message").submit();
        }
    </script>
</head>
<body>
<jsp:include page="/jsps/index/picture_nav.jsp"/><%--顶部图片和导航栏集合--%>

<div align="center">
    <div class="replyArea" style="width: 60%;">
        <h2 id="replyTitle">留言板</h2>
        <h3><a href="${pageContext.request.contextPath}/message/messageHistory.action?user.userId=${userid}">查看管理员回复</a></h3>
        <h3 style="color: #7b8b6f">${messageLeft}${error}</h3>
        <form action="${pageContext.request.contextPath}/message/leaveMessage.action?user.userId=${userid}" method="post" id="message">
            <input type="hidden" id="msgDate" name="msgDate">
            <textarea id="msgMain" maxlength="60" name="msgMain" required="required" placeholder="请在此处输入留言 =_=" style="height: 200px; width:100%;font-size: 15px;font-weight: 400;line-height: 2em;padding: 6px;"></textarea>
            <div id="Submit" align="right">
                <input type="button" onclick="CurrentTime()" value="提交"/>
            </div>
        </form>
    </div>

</div>

<jsp:include page="/jsps/index/footer.jsp"/><%--页脚--%>
</body>
</html>
