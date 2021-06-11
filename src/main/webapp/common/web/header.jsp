<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 5/14/21
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tagLib.jsp"%>

<div class="header">
    <h1 class="website-name">BKNEWS</h1>
    <div class="navbar">
        <input type="checkbox" id="chk"></input>
        <label for="chk" class="show-menu-btn">
            <i class="fas fa-bars"></i>
        </label>

        <ul class="menu">
            <label for="chk" class="hide-menu-btn">
                <i class="fas fa-times"></i>
            </label>
            <a href="<c:url value='/trang-chu'/>">Trang chủ</a>
            <c:forEach var="item" items="${categories}">
                <a href="<c:url value='/the-loai?categoryId=${item.id}'/>">${item.name}</a>
            </c:forEach>
            <c:if test="${not empty USERMODEL}">
                <a href='#'>
                    Hi,&ensp;${USERMODEL.fullName}
                </a>
                <c:if test="${USERMODEL.fullName != 'admin'}">
                    <a href='<c:url value="/thoat?action=logout"/>'>
                        Thoát
                    </a>
                </c:if>
                <c:if test="${USERMODEL.fullName == 'admin'}">
                    <a href='<c:url value="/admin-home"/>'>
                        Trang admin
                    </a>
                </c:if>
            </c:if>
            <c:if test="${empty USERMODEL}">
                <a href='<c:url value="/dang-nhap?action=login"/>'>
                    <i class="fas fa-user"></i>
                    Đăng nhập
                </a>
                <a href='<c:url value="/dang-ky?action=sign"/>'>
                    <i class="fas fa-user-plus"></i>
                    Tạo tài khoản
                </a>
            </c:if>
        </ul>
    </div>
</div>
