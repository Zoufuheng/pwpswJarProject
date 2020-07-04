<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/7
  Time: 0:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>了解我们</title>
    <link href="${pageContext.request.contextPath}/css/volunteerConsult.css" rel="stylesheet" type="text/css"/>
</head>
<body style="text-align:left">

<jsp:include page="/jsps/index/picture_nav.jsp"/>
<div class="main-content">
    <p class="main" id="main-title">关于我们</p>
    <p class="main">尊敬的朋友：</p>
    <p class="main" >您好！欢迎光临公益寻人网站！请先阅读下文来了解我们。</p>
    <p class="main">
        中国每年丢失人口有三百多人，其中孩童丢失占大多数，每一位丢失的孩童都是一个家庭巨大悲剧，
        让家人陷入巨大的悲痛之中，有的父亲母亲为了找回自己的孩子辞去工作常年在外，走遍全国各地。
        比如今年2020年的一位父亲苦苦找其孩子15年终于于孩子相认。本系统的初衷就是为了给丢失亲人的家庭一丝希望，
        让他们亲人团聚。我们将走失者的信息公布在网上，
        通过互联网的传播等手段，让更多的人知道走失者的信息，同时给予受困家庭帮助，让家人团聚。
    </p>
    <p class="main">公益寻人网的诞生</p>
    <p class="main">
        本系统所实现的功能包括走失人口信息发布，走失人口信息浏览，走失人口信息查询，修改走失人口信息，
        删除走失人口信息，评论走失人口信息，转发走失人口信息，用户登录注册，
        用户密码修改，志愿者注册，以及后台对发布的人口信息进行管理等。
    </p>
    <p class="main"> 用户可以注册成为志愿者</p>
    <p class="main">
        作为志愿者：如果您平时上网条件便利、时间固定，并且不影响到您的生活和工作，
        那么您可以加入宝贝回家志愿者QQ群，进一步的参与志愿者工作。反之，如果您的情况与上述不符，
        那么只希望您可以经常关注我们这个网站，关注我们这个社会团体。您也可以选择登记注册志愿者，
        作为一名群外志愿者，当有需要求助时可以通过您留下的联系方式来向您求得帮助。
        而当您身边遇到一些类似情况时，您可以及时与我们联系。
    </p >
    <p class="main">
        最后，感谢您的爱心及对我们的支持。如果您愿意加入到宝贝回家志愿者这个大家庭中来，
        请先加qq群1050432052咨询我们。
    </p>
    <p class="main">谢谢，欢迎您的加入！</p>

    <a style="color: blue;" href="${pageContext.request.contextPath}/index.jsp" class="vol-link">公益寻人网</a>

    <p>志愿者群1050432052</p>
    <a style="color: blue;" href="${pageContext.request.contextPath}/jsps/volunteer/volunteerRegister.jsp" class="vol-link">志愿者注册
    </a><br>
    <img width="200px" class="vol-qq" src="${pageContext.request.contextPath}/imgs/user_vol_register_img/volunteerConsult.png" alt="志愿者咨询群">

</div>
<jsp:include page="/jsps/index/footer.jsp"/>
</body>
</html>


