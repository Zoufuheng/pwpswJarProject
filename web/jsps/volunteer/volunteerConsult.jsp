<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/5/25
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>志愿者登记说明</title>
    <link href="${pageContext.request.contextPath}/css/volunteerConsult.css" rel="stylesheet" type="text/css"/>
</head>
<body style="text-align:left">

<jsp:include page="/jsps/index/picture_nav.jsp"/>
<div class="main-content">
    <p class="main" id="main-title">志愿者登记说明</p>
    <p class="main">尊敬的朋友：</p>
    <p class="main" >您好！欢迎光临公益寻人网站！在成为志愿者之前请先阅读下文来了解我们。</p>
    <p class="main">
        《公益寻人网》是隶属于公益寻人志愿者协会的公益性寻人网站，
        宝贝回家志愿者协会是关爱儿童的各界爱心人士自愿参加的民间志愿者组织，
        已在民政部门正式注册，是独具法人资格的地方性非营利社会公益团体。本协会的宗旨是：
        关爱儿童，共筑和谐。本协会成员将严格遵守国家法律法规，为失踪儿童家长提供免费的寻人帮助，
        帮助走失、被拐、被遗弃儿童寻找亲人，帮助因各种原因流浪、乞讨、卖艺儿童回归正常生活。
    </p>
    <p class="main">公益寻人网的诞生</p>
    <p class="main">
        宝贝走了，悄悄的，默默的，像天边的流星，飘流到海角，滑落到天边。
        爸爸的牵挂好多好多，妈妈的思念好苦好苦，为了找回宝贝，爸爸不惧风霜雪雨，
        妈妈不惜倾家荡产，可父母的力量终究是有限的，绝大多数的家庭至今仍没找到自己的宝贝，
        那种断肠碎心的思念，那种撕肝裂胆的痛楚，至今仍在折磨着宝贝的亲人，折磨着幼小无助的孩子，
        同样也在折磨着我们——一直关注着失踪儿童的志愿者。 我们一直想为宝贝做点事情，
        因此我们开办了这个《公益寻人网》，希望这个网站能为寻找这些失踪的宝贝提供帮助。
    </p>
    <p class="main"> 如何成为志愿者？</p>
    <p class="main">
        作为志愿者：如果您平时上网条件便利、时间固定，并且不影响到您的生活和工作，
        那么您可以加入宝贝回家志愿者QQ群，进一步的参与志愿者工作。反之，如果您的情况与上述不符，
        那么只希望您可以经常关注我们这个网站，关注我们这个社会团体。您也可以选择登记注册志愿者，
        作为一名群外志愿者，当有需要求助时可以通过您留下的联系方式来向您求得帮助。
        而当您身边遇到一些类似情况时，您可以及时与我们联系。
    </p >
    <p class="main">
        最后，感谢您的爱心及对我们的支持。如果您愿意加入到宝贝回家志愿者这个大家庭中来，
        注册志愿者之前，请先加qq群1050432052咨询我们。
        请您填写一下登记表格，然后确认提交即可。
    </p>
    <p class="main">谢谢，欢迎您的加入！</p>

    <a style="color: blue;" href="${pageContext.request.contextPath}/index.jsp" class="vol-link">公益寻人网</a>

    <p>注册志愿者之前，请先加qq群1050432052咨询我们</p>
    <a style="color: blue;" href="${pageContext.request.contextPath}/jsps/volunteer/volunteerRegister.jsp" class="vol-link">志愿者注册
    </a><br>
    <img width="200px" class="vol-qq" src="${pageContext.request.contextPath}/imgs/user_vol_register_img/volunteerConsult.png" alt="志愿者咨询群">

</div>
<jsp:include page="/jsps/index/footer.jsp"/>
</body>
</html>

