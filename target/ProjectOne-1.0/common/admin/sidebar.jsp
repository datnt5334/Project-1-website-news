<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 5/14/21
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tagLib.jsp"%>

<div class="sidebar">
    <div class="sidebar-header">
        <h3 class="brand">
            <span>BKNEWS</span>
        </h3>
        <label for="sidebar-toggle" class="ti-menu-alt"></label>
    </div>

    <div class="sidebar-menu">
        <ul>
            <li>
                <a href="<c:url value="/trang-chu"/>">
                    <span class="ti-home"></span>
                    <span>Trang chủ</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="admin-news?type=list&page=1&maxPageItem=5&sortName=createddate&sortBy=desc"/> ">
                    <span class="ti-write"></span>
                    <span>Quản lý bài viết</span>
                </a>
            </li>
        </ul>
    </div>
</div>