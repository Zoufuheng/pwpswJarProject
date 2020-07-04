<%--<%@ taglib prefix="c" uri="/struts-tags" %>--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 96989
  Date: 2020/5/18
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帖子列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/posts/postList.css">
</head>
<body>
<s:action name="showPostList" namespace="/post"></s:action>
<jsp:include page="/jsps/index/picture_nav.jsp"/><%--顶部图片和导航栏集合--%>

<div id="postListPage">

    <span class="selectArea">
        <div style="padding: 4px; margin: 10px auto;">
            <a href="${pageContext.request.contextPath}/post/Posting.action" style="display:inline-block;font-family: helvetica;font-weight: 700;line-height: 22px;font-size: 18px;color: rgba(0, 0, 0, 0.56);font-family: helvetica;">发&nbsp;&nbsp;帖</a>
        </div>
    </span>

    <div class="postList">
        <c:forEach items="${postList}" var="i">

            <div class="post">
                <h4 class="postTitle">
                    <a href="${pageContext.request.contextPath}/post/showPost.action?postId=${i.postId}">
                            ${i.postId}
                            ${i.postType}
                    </a>
                </h4>
                <div class="postText">
                    失踪地区：&nbsp;&nbsp;&nbsp;&nbsp;${i.postProvince}
                </div>
                <div class="poster">
                    版主：${i.user.userNickname}
                </div>
                <div class="time">失踪时间&nbsp;&nbsp;&nbsp;&nbsp;${i.missingtime} </div>
                <hr>
            </div>
        </c:forEach>

        <a href="${pageContext.request.contextPath}/post/showPostList.action?page=${page-1}"><span>上一页</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
        <span>第${page}页</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/post/showPostList.action?page=${page+1}"><span>下一页</span></a>&nbsp;&nbsp;&nbsp;&nbsp;

        <span>总计${count}条数据</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span>共${totalPage}页</span>

    </div>
</div>
<div id="blank" style="height: 120px"></div>
<jsp:include page="/jsps/index/footer.jsp"/><%--页脚--%>
</body>
</html>
