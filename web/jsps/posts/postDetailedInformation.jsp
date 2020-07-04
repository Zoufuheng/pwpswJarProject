<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">


    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        #detailedInformation img {
            width: 200px;
            height: 200px;
        }

        #detailedInformation table {
            margin: 0px auto;
        }

        #detailedInformation {
            margin-top: 60px;
            margin-bottom: 140px;
        }

        #detailedInformation table tr td {
            padding-top: 10px;
        }

        #detailedInformation .td1 {
            text-align: center;
        }
    </style>
</head>
<body>
<jsp:include page="/jsps/index/picture_nav.jsp"/>
<div id="detailedInformation">
    <table>
        <tr>
            <td class="td1">
                <c:if test="${empty post.postPicture}"> <img src="${pageContext.request.contextPath}/imgs/index/loss_people4.jpg"/> </c:if>
                <c:if test="${not empty post.postPicture}"> <img src="/upload/${post.postPicture}"/></c:if>
            </td>
        </tr>
        <tr>
            <td>寻亲编号:&nbsp;&nbsp;${post.postId}</td>
        </tr>
        <tr>
            <td>寻亲类别：&nbsp;&nbsp;${post.postType}</td>
        </tr>
        <tr>
            <td>姓名：&nbsp;&nbsp;${post.postName}</td>
        </tr>
        <tr>
            <td>性别：&nbsp;&nbsp;${post.postGender}</td>
        </tr>
        <tr>
            <td>年龄：&nbsp;&nbsp;${post.postAge}</td>
        </tr>
        <tr>
            <td>失踪时间：&nbsp;&nbsp;${post.missingtime}</td>
        </tr>
        <tr>
            <td>失踪地点：&nbsp;&nbsp;${post.postProvince}${post.postCity}${post.postCountry}${post.postStreet}</td>
        </tr>
        <tr>
            <td>详细描述：&nbsp;&nbsp;${post.postDescribe}</td>
        </tr>
        <tr>
            <td>联系人手机：&nbsp;&nbsp;${user.userPhone}</td>
        </tr>
        <tr>
            <td class="td1"><a href="${pageContext.request.contextPath}/index.jsp"><input type="button" value="返回主页"
                                                                                          class="btn btn-primary"></a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
