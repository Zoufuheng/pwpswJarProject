<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 96989
  Date: 2020/5/18
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帖子</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/posts/post.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
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

            document.getElementById("commentTime").value=clock;

            document.getElementById("comment").submit();
        }
    </script>
</head>
<body>
<jsp:include page="/jsps/index/picture_nav.jsp"/><%--顶部图片和导航栏集合--%>
<h2 style="color: #965454;"><a href="${pageContext.request.contextPath}/post/accusation.action?postId=${post.postId}&page=${page}&totalPage=${totalPage}&count=${count}">举报</a></h2>

<div align="center"><h3 style="color: #7b8b6f">${accusation}<br>${error}</h3></div>
<div id="postPage">

    <%--主贴--%>
    <div class="post">
        <%--发帖人信息--%>
        <h4 class="poster" style="color:#965454;">
            <c:if test="${not empty post.user.userPicture}">
                <img src="/upload/${post.user.userPicture}" >
            </c:if>

            <c:if test="${empty post.user.userPicture}">
                <img src="${pageContext.request.contextPath}/imgs/login/userInPost.png"/>
            </c:if>
            <br><br>
            版主：${post.user.userNickname}<br>
        </h4>
        <%--帖子详情    --%>
        <div class="postDetail">
            寻人类型：${post.postType}<br>
            失踪者姓名：${post.postName}<br>
            失踪者性别：${post.postGender}<br>
            失踪者年龄：${post.postAge}<br>
            失踪时间：${post.missingtime}<br>
            失踪省份：${post.postProvince}<br>
            失踪城市：${post.postCity}<br>
            失踪乡镇：${post.postCountry}<br>
            失踪街道：${post.postStreet}<br>
            详情描述：${post.postDescribe}<br>

            <c:if test="${not empty post.postPicture }">图片：<br><img src="/upload/${post.postPicture}" width="400" height="400"></c:if>
            <%--<c:if test="${empty post.postPicture }"><img src="${pageContext.request.contextPath}/imgs/index/loss_people1.jpg" width="400" height="400"></c:if>--%>
            <br><br>
            <div class="time">发帖时间&nbsp;&nbsp;&nbsp;&nbsp;${post.postTime}</div>
            <div class="reply"><a href="#replyPoint">回复</a></div>
        </div>
        <div class="clear"></div>
    </div>

    <c:forEach items="${comments}" var="i">
        <div class="post">
            <h4 class="poster"  style="color: #7b8b6f;">
                <c:if test="${not empty i.user.userPicture}">
                    <img src=" /upload/${i.user.userPicture}" >
                </c:if>
                <c:if test="${empty i.user.userPicture}">
                    <img src="${pageContext.request.contextPath}/imgs/login/userInPost.png"/>
                </c:if>
                <br><br>
                用户：${i.user.userNickname}<br>
            </h4>
            <div class="postDetail">
                    ${i.comments}
                <br><br><br>
                <div class="time">评论时间&nbsp;&nbsp;&nbsp;&nbsp;${i.commentTime}</div>
                <div class="reply"><a href="#replyPoint">回复</a></div>
            </div>
            <div class="clear"></div>
        </div>
    </c:forEach>

    <br><br><br>
    <a href="${pageContext.request.contextPath}/post/showPost.action?page=${page-1}&postId=${post.postId}"><span>上一页</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
    <span>第${page}页</span>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/post/showPost.action?page=${page+1}&postId=${post.postId}"><span>下一页</span></a>&nbsp;&nbsp;&nbsp;&nbsp;

    <span>总计${count}条评论</span>&nbsp;&nbsp;&nbsp;&nbsp;
    <span>共${totalPage}页</span>
    <br><br><br>

    <%--回复区域--%>
    <div class="replyArea">
        <a name="replyPoint"></a>
        <h2 id="replyTitle">回复</h2>
        <form action="${pageContext.request.contextPath}/comment/insertComment.action?user.userId=${userid}&page=${page}" method="post" id="comment">
            <input type="hidden" id="postId" name="postId" value="${post.postId}">
            <input type="hidden" id="commentTime" name="commentTime">
            <input type="hidden" id="totalPage" name="totalPage" value="${totalPage}">
            <input type="hidden" id="count" name="count" value="${count}">

            <textarea id="comments" maxlength="90" name="comments" required="required"  placeholder="请在这儿输入回复吧 =_=" style="height: 200px; width:100%;font-size: 15px;font-weight: 400;line-height: 2em;padding: 6px;"></textarea>
            <%--上传图片<input type="file" id="imageFile" name="imageFile" accept="image/gif, image/jpeg, image/png, image/jpg">--%>

            <div id="postSubmit">
                <input type="button" onclick="CurrentTime()" value="提交"/>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/jsps/index/footer.jsp"/><%--页脚--%>
</body>
</html>
