<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>丢失名单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index/listPage.css">
    <script>
        // 鼠标移动切换显示内容
        function setTab(nav, cursel, n) {
            for (i = 1; i <= n; i++) {
                var menu = document.getElementById(nav + i);
                var con = document.getElementById("con_" + nav + i);
                menu.className = i == cursel ? "hover" : "";
                con.style.display = i == cursel ? "block" : "none";
            }
        }
    </script>
</head>
<body>
<s:action name="showWantedInformationOfIndex" namespace="/post"/>

<div class="course_list">
    <div class="course_nav">
        <a id="nav2_1" onmousemove="setTab('nav2_',1,5)" class="selected" href="#">家寻宝贝</a>
        <a id="nav2_2" onmousemove="setTab('nav2_',2,5)" class="selected" href="#">宝贝寻家</a>
        <a id="nav2_3" onmousemove="setTab('nav2_',3,5)" class="selected" href="#">流浪乞讨</a>
        <a id="nav2_4" onmousemove="setTab('nav2_',4,5)" class="selected" href="#">海外寻亲</a>
        <a id="nav2_5" onmousemove="setTab('nav2_',5,5)" class="selected" href="#">其他寻人</a>
    </div>
    <h4>如果你见到他们 请联系我们</h4>
    <div class="course_container">
        <!-- 家寻宝贝内容 -->
        <div id="con_nav2_1" class="nav_list">
            <c:forEach items="${informationList1}" var="i">
                <div class="course">
                    <a href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">
                        <c:if test="${empty i.postPicture}"><img
                                src="${pageContext.request.contextPath}/imgs/index/loss_people1.jpg" alt="HTML"></c:if>
                        <c:if test="${not empty i.postPicture}"><img src="/upload/${i.postPicture}" alt="HTML"></c:if>
                    </a>
                    <span class="course_title"><a
                            href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">${i.postProvince} ${i.postCity}</a></span>
                    <img class="person" src="${pageContext.request.contextPath}/imgs/index/person.png" alt="person">
                    <span class="number">323</span>
                </div>
            </c:forEach>
        </div>
        <!-- 宝贝寻家内容 -->
        <div id="con_nav2_2" style="display: none" class="nav_list">
            <c:forEach items="${informationList2}" var="i">
                <div class="course">
                    <a href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">
                        <c:if test="${empty i.postPicture}"><img
                                src="${pageContext.request.contextPath}/imgs/index/loss_people1.jpg" alt="HTML"></c:if>
                        <c:if test="${not empty i.postPicture}"><img src="/upload/${i.postPicture}" alt="HTML"></c:if>
                    </a>
                    <span class="course_title"><a
                            href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">${i.postProvince} ${i.postCity}</a></span>
                    <img class="person" src="${pageContext.request.contextPath}/imgs/index/person.png" alt="person">
                    <span class="number">323</span>
                </div>
            </c:forEach>
        </div>
        <!--流浪乞讨内容 -->
        <div id="con_nav2_3" style="display: none" class="nav_list">
            <c:forEach items="${informationList3}" var="i">
                <div class="course">
                    <a href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">
                        <c:if test="${empty i.postPicture}"><img
                                src="${pageContext.request.contextPath}/imgs/index/loss_people1.jpg" alt="HTML"></c:if>
                        <c:if test="${not empty i.postPicture}"><img src="/upload/${i.postPicture}" alt="HTML"></c:if>
                    </a>
                    <span class="course_title"><a
                            href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">${i.postProvince} ${i.postCity}</a></span>
                    <img class="person" src="${pageContext.request.contextPath}/imgs/index/person.png" alt="person">
                    <span class="number">323</span>
                </div>
            </c:forEach>
        </div>
        <!--海外寻人内容 -->
        <div id="con_nav2_4" style="display: none" class="nav_list">
            <c:forEach items="${informationList4}" var="i">
                <div class="course">
                    <a href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">
                        <c:if test="${empty i.postPicture}"><img
                                src="${pageContext.request.contextPath}/imgs/index/loss_people1.jpg" alt="HTML"></c:if>
                        <c:if test="${not empty i.postPicture}"><img src="/upload/${i.postPicture}" alt="HTML"></c:if>
                    </a>
                    <span class="course_title"><a
                            href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">${i.postProvince} ${i.postCity}</a></span>
                    <img class="person" src="${pageContext.request.contextPath}/imgs/index/person.png" alt="person">
                    <span class="number">323</span>
                </div>
            </c:forEach>
        </div>
        <!--其他寻人内容 -->
        <div id="con_nav2_5" style="display: none" class="nav_list">
            <c:forEach items="${informationList5}" var="i">
                <div class="course">
                    <a href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">
                        <c:if test="${empty i.postPicture}"><img src="${pageContext.request.contextPath}/imgs/index/loss_people1.jpg" alt="HTML"></c:if>
                        <c:if test="${not empty i.postPicture}"><img src="/upload/${i.postPicture}" alt="HTML"></c:if>
                    </a>
                    <span class="course_title"><a
                            href="${pageContext.request.contextPath}/post/showDetailedInformation.action?postId=${i.postId}">${i.postProvince} ${i.postCity}</a></span>
                    <img class="person" src="${pageContext.request.contextPath}/imgs/index/person.png" alt="person">
                    <span class="number">323</span>
                </div>
            </c:forEach>
        </div>

        <div class="clear"></div>
    </div>
</div>
</body>
</html>
