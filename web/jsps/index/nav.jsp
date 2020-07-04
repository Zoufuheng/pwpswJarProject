<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>导航栏</title>
	<style type="text/css">
		#nav ul li{
			display: inline;
			list-style-type:none;
			height: 42px;
			width: 135px;
			float:left;
			line-height:39px;
		}
		#nav ul li a{
			text-decoration: none;
			color: white;
		}
		#nav ul li:hover{
			background-color: #E92E1D;
		}
		#nav ul li:hover .nav1{
			display: block;
		}
		#nav ul li .nav1{
			display: none;
		}
		#nav{
			background-color:#C82719;
			padding-left:330px;
			text-align: center;
			margin: 0px auto;
		}
		#nav ul{
			margin: 0px auto;
			padding:0px;
			height: 42px;
			width:100%;
		}
		body{
			text-align:center;
			margin: 0;
			width: 100%;
		}
		#nav .nav1 ul li:hover{
			background-color: #E92E1D;
		}
		.nav1 ul li{
			background-color: white;
		}
		#nav .nav1 ul li  a{
			text-decoration: none;
			color: black;
		}
		.nav1{
			margin-top:4px;
			position: relative;
			z-index: 2;
		}
	</style>
</head>
<body>
<div id="nav">
	<ul>
		<li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
		<li><a href="${pageContext.request.contextPath}/jsps/posts/posting.jsp">寻亲登记</a>
			<div class="nav1">
				<ul>
					<li><a href="${pageContext.request.contextPath}/post/showPostClass.action?postType=家寻宝贝&page=1&rows=10">家寻宝贝</a></li>
					<li><a href="${pageContext.request.contextPath}/post/showPostClass.action?postType=宝贝寻家&page=1&rows=10">宝贝寻家</a></li>
					<li><a href="${pageContext.request.contextPath}/post/showPostClass.action?postType=流浪乞讨&page=1&rows=10">流浪乞讨</a></li>
					<li><a href="${pageContext.request.contextPath}/post/showPostClass.action?postType=其他寻人&page=1&rows=10">其他寻人</a></li>
					<li><a href="${pageContext.request.contextPath}/post/showPostClass.action?postType=海外寻亲&page=1&rows=10">海外寻亲</a></li>
				</ul>
			</div>
		</li>
		<li><a href="${pageContext.request.contextPath}/jsps/volunteer/volunteerConsult.jsp">志愿者登记</a></li>
		<li><a href="${pageContext.request.contextPath}/post/showPostList.action?page=1">论坛</a></li>
		<li><a href="${pageContext.request.contextPath}/jsps/myAccount/myAccount.jsp">我的</a></li>
		<li><a href="${pageContext.request.contextPath}/notice/noticeList.action?page=1&rows=10">网站通告</a></li>
	</ul>
</div>
</body>
</html>