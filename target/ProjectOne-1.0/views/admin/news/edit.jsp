<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 6/2/21
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tagLib.jsp"%>
<c:url var="APIurl" value="/api-admin-news" />
<c:url var="NewURL" value="/admin-news" />
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="list-manager">
    <c:if test="${not empty model.id}">
        <h5>Chỉnh sửa bài viết</h5>
    </c:if>
    <c:if test="${empty model.id}">
        <h5>Thêm bài viết</h5>
    </c:if>

    <br>
    <form id="formSubmit">
        <input type="hidden" value="${model.id}" id="id" name="id"/>
        <input type="hidden" value="${model.thumbnail}" id="thumbnail" name="thumbnail"/>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
            <div class="col-sm-3">
                <select class="form-control" id="categoryCode" name="categoryCode">
                    <c:if test="${empty model.categoryCode}">
                        <option>Chọn loại bài viết</option>
                        <c:forEach var="item" items="${categories}">
                            <option value="${item.code}">${item.name}</option>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty model.categoryCode}">
                        <option>Chọn loại bài viết</option>
                        <c:forEach var="item" items="${categories}">
                            <option value="${item.code}"  <c:if test="${item.code == model.categoryCode}">selected="selected"</c:if>>
                                    ${item.name}
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <br /> <br />
        <div class="form-group">
            <label class="col-sm-3">Tiêu đề</label>
            <div class="col-sm-3 content-rps">
                <textarea id="title" name="title">${model.title}</textarea>
            </div>
        </div>
        <br /> <br />
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right">Hình
                đại diện</label>
            <div class="col-sm-9 img-rps">
                <input type="file" onchange="previewFile()">
                <br /> <br />
                <img src="${model.thumbnail}" height="200" width="300" alt="Image preview...">
            </div>
        </div>
        <br /> <br />
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right">Mô
                tả ngắn</label>
            <div class="col-sm-9 content-rps">
                <textarea id="shortDescription" name="shortDescription">${model.shortDescription}
                </textarea>
            </div>
        </div>
        <br /> <br />
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right">Nội
                dung</label>
            <div class="col-sm-9">
            <textarea rows="" cols="" id="content" name="content">
                ${model.content}
            </textarea>
            </div>
        </div>
        <div class="form-group">
            <c:if test="${not empty model.id}">
                <input type="button" class="btn btn-white btn-warning btn-bold"
                       value="Cập nhật bài viết" id="btnAddOrUpdateNew" />
            </c:if>
            <c:if test="${empty model.id}">
                <input type="button" class="btn btn-white btn-warning btn-bold"
                       value="Thêm bài viết" id="btnAddOrUpdateNew" />
            </c:if>
        </div>
    </form>
</div>

<div class="summary">
    <div class="summary-info">
        <div class="summary-single">
            <span class="fas fa-user-alt"></span>
            <div>
                <h5 id="counter" data-target="${totalUser}">0</h5>
                <small>Tài khoản</small>
            </div>
        </div>
        <div class="summary-single">
            <span class="fas fa-file-alt"></span>
            <div>
                <h5 id="counter" data-target="${totalNews}">0</h5>
                <small>Bài viết</small>
            </div>
        </div>
        <div class="summary-single">
            <span class="fas fa-comment-alt"></span>
            <div>
                <h5 id="counter" data-target="${totalComment}">0</h5>
                <small>Bình luận</small>
            </div>
        </div>
    </div>
</div>

<script>
    let editor;
    $( document ).ready(function() {
        editor = CKEDITOR.replace('content');
    });

    function previewFile() {
        const preview = document.querySelector('img');
        const file = document.querySelector('input[type=file]').files[0];
        const reader = new FileReader();

        reader.addEventListener("load", function () {
            // convert image file to base64 string
            preview.src = reader.result;
            if (reader.result != null) {
                $('#thumbnail').val(reader.result);
            }
        }, false);

        if (file) {
            reader.readAsDataURL(file);
        }
    }

    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        data["content"] = editor.getData();
        var id = $('#id').val();
        if (id == "") {
            addNews(data);
        } else {
            updateNews(data);
        }
    });

    function addNews(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                <c:url var="NewsURL" value="/admin-news">
                <c:param name="type" value="list"/>
                <c:param name="page" value="1"/>
                <c:param name="maxPageItem" value="3"/>
                <c:param name="sortName" value="createddate"/>
                <c:param name="sortBy" value="desc"/>
                <c:param name="message" value="insert_success"/>
                </c:url>
                window.location.href = "${NewsURL}";
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

    function updateNews(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                <c:url var="NewsURL" value="/admin-news">
                <c:param name="type" value="list"/>
                <c:param name="page" value="1"/>
                <c:param name="maxPageItem" value="3"/>
                <c:param name="sortName" value="createddate"/>
                <c:param name="sortBy" value="desc"/>
                <c:param name="message" value="update_success"/>
                </c:url>
                window.location.href = "${NewsURL}";
            },
            error: function (error) {
                console.log(error);
            }
        });
    }
</script>
</body>
</html>
