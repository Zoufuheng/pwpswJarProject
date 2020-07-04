<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 96989
  Date: 2020/5/24
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/posts/posting.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/user_vol_register_js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/user_vol_register_js/distpicker.data.js"></script>
    <script src="${pageContext.request.contextPath}/js/user_vol_register_js/distpicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/posting/posting.js"></script>
</head>
<body>
<jsp:include page="/jsps/index/picture_nav.jsp"/>
<%--顶部图片和导航栏集合--%>
<%--获取当前时间--%>
<%
    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    String now=df.format(new Date());
%>

<%--start Yani的jsp--%>
<div class="rg_layout">
    <div class="rg_left">
        <p id="post-posting">寻亲登记</p>
    </div>
    <%--end左浮动结束--%>
    <div class="rg_center">
        <div class="rg_form"><%--定义表单--%>
            <form action="${pageContext.request.contextPath}/post/posting.action?user.userId=${userid}" method="post" id="form">
                <table>

                    <span><input type="date" id="postTime" name="postTime" value="<%= now%>" style="display:none"></span>
                    <tr>
                        <td class="td_left"><label for="postType"><span class="requirement">*</span>寻亲类型</label></td>
                        <td class="td_right" colspan="3">
                            <select name="postType" name="postType" id="postType">
                                <option value="家寻宝贝">家寻宝贝</option>
                                <option value="宝贝寻家">宝贝寻家</option>
                                <option value="流浪乞讨">流浪乞讨</option>
                                <option value="海外寻亲">海外寻亲</option>
                                <option value="其他寻人">其他寻人</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="postName"><span class="requirement">*</span>姓名</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="postName" id="postName" placeholder="请输入姓名">
                            <span id="s_postName" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="postAge"><span class="requirement">*</span>年龄</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="postAge" id="postAge" placeholder="请输入年龄">
                            <span id="s_postAge" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="man">性别</label></td>
                        <td class="td_right" colspan="3">
                            <input id="man" type="radio" checked="checked" name="postGender" value="男"/><span
                                class="man">男</span><input
                                id="woman" type="radio" name="postGender" value="女"/><span>女</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="missingtime"><span class="requirement">*</span>失踪时间</label></td>
                        <td class="td_right" colspan="3">
                            <input type="date" name="missingtime" id="missingtime" value max="<%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10) %>">
                            <span id="s_missingtime" class="error"></span>
                        </td>
                    </tr>
                    <tr data-toggle="distpicker">
                        <td class="td_left"><label>省份</label></td>
                        <td class="td_right "><select id="province1" name="postProvince"></select></td>
                        <td class="td_left"><label>城市</label></td>
                        <td class="td_right"><select id="city1" name="postCity"></select></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="postCountry">失踪村子</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="postCountry" id="postCountry" placeholder="请输入失踪村子">
                            <span id="s_postCountry" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="postStreet">失踪街道</label></td>
                        <td class="td_right" colspan="3">
                            <input type="text" name="postStreet" id="postStreet" placeholder="请输入失踪街道">
                            <span id="s_postStreet" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="postPicture">照片</label></td>
                        <td class="td1" colspan="3">
                            <div align="center">
                                <input type="file" name="picture" id="postPicture">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="postDescribe">详细描述</label></td>
                        <td class="td_right" colspan="3">
                            <%--                            <input type="textarea" name="postDescribe" id="postDescribe" placeholder="详细描述/联系方式">--%>
                            <textarea name="postDescribe" id="postDescribe" placeholder="详细描述/联系方式"></textarea>
                            <span id="s_postDescribe" class="error"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="check_code"><span class="requirement">*</span>验证码</label></td>
                        <td class="td_right" colspan="4">
                            <div class="code">
                                <input type="text" value="" placeholder="请输入验证码（不区分大小写）" id="check_code"
                                       class="input-val">
                                <canvas id="canvas" width="100" height="30"></canvas>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="btn_left" align="center" colspan="4">
                            <input type="submit" name="btn_posting" value="提交" id="btn_posting" class="btn">
                            <input type="submit" name="btn_posting" value="重置" id="btn_reset">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4"><s:fielderror/></td>
                    </tr>
                </table>
            </form>
        </div>
        <%--end定义表单--%>
    </div>
</div>
<%--end Yani的jsp--%>


<jsp:include page="/jsps/index/footer.jsp"/>
<%--页脚--%>
</body>
</html>
