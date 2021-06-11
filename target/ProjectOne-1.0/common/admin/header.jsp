<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 5/14/21
  Time: 3:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tagLib.jsp"%>

<header>

    <div class="search-wrapper">
        <span class="ti-search"></span>
        <input type=search placeholder="Tìm kiếm">
    </div>

    <div class="account">
                <span>
                    <a href=""> <i class="fas fa-user-circle"></i> Xin chào, ${USERMODEL.fullName}</a>
                </span>
        <a href="<c:url value='/thoat?action=logout'/>">
            <i class="fas fa-sign-out-alt"></i> Thoát
        </a>
    </div>

</header>
