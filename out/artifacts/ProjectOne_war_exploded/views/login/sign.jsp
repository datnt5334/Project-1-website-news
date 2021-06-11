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
    <title>Đăng ký</title>
</head>

<body>
<div class="center">
    <h1>Sign up</h1>
    <form method="post">
        <div class="txt_field">
            <input type="text" required>
            <span></span>
            <label>Tài khoản</label>
        </div>
        <div class="txt_field">
            <input type="password" id="password" required>
            <span></span>
            <label>Mật khẩu</label>
        </div>
        <div class="txt_field">
            <input type="password" id="confirm_password" required>
            <span></span>
            <label>Nhập lại mật khẩu</label>
        </div>
        <input type="submit" value="Đăng ký">
        <div class="signup_link">
            Đã có tài khoản ? <a href="<c:url value='/dang-nhap?action=login'/>">Đăng nhập</a>
        </div>
    </form>
</div>
</body>
</html>
