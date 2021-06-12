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
        <h2>Ý kiến bạn đọc</h2>
        <div class="textarea-config">
            <textarea rows="4" cols="72" placeholder="Ý kiến của bạn"></textarea>
        </div>
        <div class="submit-config">
            <span id="name">Nguyễn Thành Đạt</span>
            <button>Gửi</button>
        </div>
        <h4>Bình luận mới nhất</h4>
        <hr>
        <div class="comment">
            <div class="comment-left">
                <div class="profileImage"></div>
            </div>
            <div class="comment-right">
                <p>
                    <strong>Nguyễn Thành Đạt</strong>
                    Bai viet nay rat la hay!
                </p>
            </div>
            <div class="comment-left">
                <div class="profileImage"></div>
            </div>
            <div class="comment-right">
                <p>
                    <strong>Nguyễn Thành Đạt</strong>
                    Bai viet nay rat la hay!
                    Bai viet nay rat la hay!
                    Bai viet nay rat la hay!
                    Bai viet nay rat la hay!
                    Bai viet nay rat la hay!
                </p>
            </div>
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
<script>
    $(document).ready(function(){
        var intials = $('#name').text().charAt(0);
        var profileImage = $('.profileImage').text(intials);
    });
</script>
</body>
</html>
