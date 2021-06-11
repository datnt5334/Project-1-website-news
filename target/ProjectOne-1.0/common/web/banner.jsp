<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 5/14/21
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tagLib.jsp"%>

<section class="banner">
    <div class="banner-main-content">
        <h2>Nhận những thông tin nóng hổi nhất</h2>
        <h3>Cổng thông tin hàng đầu Việt Nam</h3>

        <button>
            <a href="#">Tìm hiểu thêm</a>
        </button>

        <div class="current-news-head">
            <c:forEach var="item" items="${model1.listResult}" end="3">
                <h3>${item.title}</h3>
            </c:forEach>
        </div>
    </div>
    <div class="banner-sub-content">
        <c:forEach var="item" items="${model1.listResult}" end="3">
            <div class="hot-topic">
                <img src="${item.thumbnail}">

                <div class="hot-topic-content">
                    <h2>${item.title}</h2>
                    <p>${item.shortDescription}</p>
                    <c:forEach var="item2" items="${categories}">
                        <c:if test="${item2.id == item.categoryId}">
                            <h3>${item2.name}</h3>
                        </c:if>
                    </c:forEach>
                    <a href="<c:url value='/chi-tiet?id=${item.id}'/>">Đọc thêm</a>
                </div>
            </div>
        </c:forEach>
    </div>
</section>