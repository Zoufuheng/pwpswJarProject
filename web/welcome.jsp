<%--
  Created by IntelliJ IDEA.
  User: 75164
  Date: 2020/5/18
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page isELIgnored="false" %>
<html>

<head>

    <title>正在跳转</title>
</head>


<body>

<%--<s:action name="list" namespace="/user"></s:action>
${username}--%>


<form action="${pageContext.request.contextPath}/user/submitpicture.action" method="post" enctype="multipart/form-data">
    username:<input type="text" name="username"> <br>
    userPassword<input type="text" name="userPassword"> <br>
    userGender<input type="text" name="userGender"><br>
    userAge<input type="text" name="userAge"><br>
    userProvince<input type="text" name="userProvince"> <br>
    userCity<input type="text" name="userCity"><br>
    userNickname<input type="text" name="userNickname"><br>
    userPhone <input type="text" name="userPhone"><br>
    picture<input type="file" name="picture"/></td>
    <input type="submit">
    
</form>
<img src="" alt="">
</body>

</html>d

