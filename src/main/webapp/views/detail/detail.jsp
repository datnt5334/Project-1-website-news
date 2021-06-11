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
    <div  class="main-container-left">
        <h1>${model1.title}</h1>
        <p>${model1.shortDescription}</p>
        <img src="${model1.thumbnail}">
        <div>
            ${model1.content}
        </div>
    </div>

    <section class="main-container-right">
        <h2>Tin tức mới nhất</h2>

        <c:forEach var="item" items="${model2.listResult}">
            <article>
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
