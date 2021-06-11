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
    <title>BKNEWS - Trang báo điện tử</title>
</head>

<body>
<main>
    <section class="main-container-left">
        <h2>Tin tức nổi bật</h2>
        <div class="container-top-left">
            <c:forEach var="item" items="${model1.listResult}" begin="4" end="4">
                <article>
                    <img src="${item.thumbnail}">
                    <div>
                        <h3>${item.title}</h3>
                        <p>${item.shortDescription}</p>
                        <a href="<c:url value='/chi-tiet?id=${item.id}'/>">Đọc thêm<span>>></span></a>
                    </div>
                </article>
            </c:forEach>
        </div>

        <div class="container-bottom-left">
            <c:forEach var="item" items="${model1.listResult}" begin="5" end="6">
                <article>
                    <img src="${item.thumbnail}">
                    <div>
                        <h3>${item.title}</h3>
                        <p>${item.shortDescription}</p>

                        <a href="<c:url value='/chi-tiet?id=${item.id}'/>">Đọc thêm<span>>></span></a>
                    </div>
                </article>
            </c:forEach>
        </div>
    </section>

    <section class="main-container-right">
        <h2>Tin tức mới nhất</h2>

        <c:forEach var="item" items="${model2.listResult}">
            <article>
                <h4>Vừa ra</h4>
                <div>
                    <h2>${item.title}</h2>

                    <p>${item.shortDescription}</p>
                    <a href="<c:url value='/chi-tiet?id=${item.id}'/>">Đọc thêm<span>>></span></a>
                </div>
                <img src="${item.thumbnail}">

            </article>
        </c:forEach>
    </section>
</main>
</body>
</html>
