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
<input type="hidden" value="${category.id}" id="categoryId" name="categoryId" />
<input type="hidden" value="${model1.totalItem}" id="totalItems" name="totalItems" />
<section class="banner">
    <div class="banner-left">
        <div class="banner-left-top">
            <h1>${category.name}</h1>
        </div>
        <div class="banner-left-bottom">
            <c:forEach var="item" items="${model1.listResult}" end="3">
                <a href="<c:url value='/chi-tiet?id=${item.id}'/>">
                    <h3>${item.title}</h3>
                </a>
                <p>${item.shortDescription}</p>
                <hr>
            </c:forEach>
        </div>
    </div>
    <div class="banner-right">
        <c:forEach var="item" items="${model1.listResult}" begin="4" end="5">
            <article>
                <img src="${item.thumbnail}">
                <div>
                    <a href="<c:url value='/chi-tiet?id=${item.id}'/>">
                        <h3>${item.title}</h3>
                    </a>
                    <p>${item.shortDescription}</p>
                </div>
            </article>
        </c:forEach>
    </div>
</section>

<hr>

<main>

    <section class="main-container-left">
        <h2>Tin tức mới nhất</h2>
        <div id="content">
            <c:forEach var="item" items="${model2.listResult}">
                <article class="load load-more">
                    <div>
                        <h2>${item.title}</h2>

                        <p>${item.shortDescription}</p>
                        <a href="<c:url value='/chi-tiet?id=${item.id}'/>">Đọc thêm<span>>></span></a>
                    </div>
                    <img src="${item.thumbnail}">

                </article>
            </c:forEach>
        </div>
        <button onclick="loadMore()" class="btn-load" id="loadBtn">Xem thêm</button>
    </section>
</main>
<script>
    function loadMore(){
        var amount = document.getElementsByClassName("load").length;
        var categoryid = document.getElementById("categoryId").value;
        var limit = 3;
        var totalItems = document.getElementById("totalItems").value;
        $.ajax({
            url: '<c:url value="/load-category"/>',
            type: 'GET',
            data: {
                offset: amount,
                categoryId: categoryid,
                limit: limit
            },
            success: function (result) {
                var row = document.getElementById('content');
                row.innerHTML += result;
                if ((amount+limit) == totalItems) {
                    document.getElementById('loadBtn').style.display = "none";
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    }
</script>
</body>
</html>
