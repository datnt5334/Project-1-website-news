<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 5/14/21
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tagLib.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
</head>

<body>
<c:if test="${not empty message}">
    <div class="alert alert-${alert} alert-dismissable" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
        <strong>${message}</strong>
    </div>
</c:if>
<div class="center">
    <h1>Login</h1>
    <form action= "<c:url value='/dang-nhap'/>" method="POST">
        <div class="txt_field">
            <input type="text" name="userName" required>
            <span></span>
            <label>Tài khoản</label>
        </div>
        <div class="txt_field">
            <input type="password" name="password" required>
            <span></span>
            <label>Mật khẩu</label>
        </div>
        <input type ="hidden" value="login" name="action">
        <input type="submit" value="Đăng nhập">
        <div class="signup_link">
            Chưa có tài khoản ? <a href="<c:url value='/dang-ky?action=sign'/>">Đăng ký</a>
        </div>
    </form>
</div>
</body>
</html>
