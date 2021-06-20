<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 5/14/21
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tagLib.jsp"%>
<c:url var="APIurl" value="/api-comment" />

<!DOCTYPE html>
<html>
<head>
    <title>BKNEWS - Trang báo điện tử</title>
</head>

<body>
<input type="hidden" value="${comment.totalItem}" id="totalItems" name="totalItems" />
<input type="hidden" value="${model1.id}" id="newsId" name="IdOfNews" />
<input type="hidden" value="${USERMODEL.userName}" id="username" name="username" />
<input type="hidden" value="${USERMODEL.roleId}" id="roleId" name="roleId" />
<main>
    <div class="main-container-left">
        <h1>${model1.title}</h1>
        <p>${model1.shortDescription}</p>
        <img src="${model1.thumbnail}">
        <div>
            ${model1.content}
        </div>
        <h2>Ý kiến bạn đọc</h2>
            <c:if test="${not empty USERMODEL}">
                <form id="commentSubmit">
                    <input type="hidden" name="userId" value="${USERMODEL.id}">
                    <input type="hidden" name="newsId" value="${model1.id}">
                    <div class="textarea-config">
                        <textarea rows="4" cols="72" placeholder="Ý kiến của bạn" name="content"></textarea>
                    </div>
                    <div class="submit-config">
                        <span>${USERMODEL.fullName}</span>
                        <button id="contentSubmit">Gửi</button>
                    </div>
                    <h4>Bình luận mới nhất</h4>
                    <hr>
                </form>
            </c:if>
        <div class="comment" id="content">
            <c:forEach var="item" items="${comment.listResult}">
                <div class="comment-left load">
                    <div class="profileImage" id="profile_${item.id}">${item.user.fullName}</div>
                </div>
                <div class="comment-right">
                    <p>
                        <strong>
                            <span>${item.user.fullName}</span>
                        </strong>
                        ${item.content}
                    </p>
                    <c:if test="${not empty USERMODEL}">
                        <c:if test="${USERMODEL.roleId == 1}">
                            <a href="#" class="remove-item" id="${item.id}" data-toggle="tooltip" title='Xóa bình luận'>
                                <span class="fas fa-times"></span>
                            </a>
                        </c:if>
                        <c:if test="${USERMODEL.roleId == 2 && USERMODEL.userName == item.user.userName}">
                            <a href="#" class="remove-item" id="${item.id}" data-toggle="tooltip" title='Xóa bình luận'>
                                <span class="fas fa-times"></span>
                            </a>
                        </c:if>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <c:if test="${comment.totalItem > 3}">
            <button onclick="loadMore()" class="btn-load" id="loadBtn">Xem thêm</button>
        </c:if>
        <c:if test="${empty comment.listResult}">
            <div class="no-comment">
                <h3>Chưa có bình luận nào</h3>
            </div>
        </c:if>
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

    $( document ).on('click', ".remove-item", function() {
        let data = {};
        const test = [];
        test[0] = this.id;
        let ids = test;
        data['ids'] = ids;
        deleteComment(data);
    });

    function loadMore(){
        var id = document.getElementById("newsId").value;
        var offset = document.getElementsByClassName("load").length;
        var limit = 3;
        var totalItems = document.getElementById("totalItems").value;
        var userName = document.getElementById("username").value;
        var roleId = document.getElementById("roleId").value;
        $.ajax({
            url: '<c:url value="/load-comment"/>',
            type: 'GET',
            data: {
                id: id,
                offset: offset,
                limit: limit,
                username: userName,
                roleId: roleId,
            },
            success: function (result) {
                var row = document.getElementById('content');
                row.innerHTML += result;
                if ((offset+limit) >= totalItems) {
                    document.getElementById('loadBtn').style.display = "none";
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

    $('#contentSubmit').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#commentSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        addComment(data);
    });

    function addComment(data){
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                <c:url var="DetailURL" value="/chi-tiet">
                   <c:param name="id" value="${model1.id}"/>
                </c:url>
                window.location.href = "${DetailURL}";
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

    function deleteComment(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                <c:url var="DetailURL" value="/chi-tiet">
                <c:param name="id" value="${model1.id}"/>
                </c:url>
                window.location.href = "${DetailURL}";
            },
            error: function (error) {
                console.log(error);
            }
        });
    }


</script>
</body>
</html>
