<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 5/30/21
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tagLib.jsp"%>
<c:url var="APIurl" value="/api-admin-news" />
<html>
<head>
    <title>Danh sách bài viết</title>
</head>
<body>
<form action="<c:url value='/admin-news'/>" id="formSubmit" method="get">
    <input type="hidden" value="" id="page" name="page" />
    <input type="hidden" value="" id="maxPageItem" name="maxPageItem" />
    <input type="hidden" value="" id="sortName" name="sortName" />
    <input type="hidden" value="" id="sortBy" name="sortBy" />
    <input type="hidden" value="" id="type" name="type" />
    <div class="list-manager">
        <h5>Quản lý bài viết
            <a href="#" id="btnDelete" class="remove-item" data-toggle="tooltip" title='Xóa bài viết'>
                <span class="fas fa-trash-alt"></span>
            </a>
            <a href="<c:url value='/admin-news?type=edit'/>" class="add-item" data-toggle="tooltip" title='Thêm bài viết'>
                <span class="fas fa-plus-circle"></span>
            </a>
        </h5>
        <form>
            <c:if test="${not empty message}">
                <div class="alert alert-${alert} alert-dismissable" role="alert">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                    <strong>${message}</strong>
                </div>
            </c:if>
        </form>
        <div class="table-responsive">
            <table>
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="removeAll" onClick="toggle(this)">
                    </th>
                    <th>Tên bài viết</th>
                    <th>Thể loại</th>
                    <th>Mô tả ngắn</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${model.listResult}">
                    <tr>
                        <td style="width: 5%;">
                            <input type="checkbox" id="checkbox_${item.id}" value="${item.id}" name="remove">
                        </td>
                        <td style="width: 20%;">${item.title}</td>
                        <td style="width: 15%;">
                            <c:forEach var="item2" items="${categories}">
                                <c:if test="${item2.id == item.categoryId}">${item2.name}</c:if>
                            </c:forEach>
                        </td>
                        <td style="width: 40%;">${item.shortDescription}</td>
                        <td style="width: 20%;">
                            <c:url var="editURL" value="/admin-news">
                                <c:param name="type" value="edit"/>
                                <c:param name="id" value="${item.id}"/>
                            </c:url>
                            <a href="${editURL}" data-toggle="tooltip" title='Chỉnh sửa bài viết'>
                                <span class="fas fa-edit"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
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
    <ul class="pagination justify-content-center" id="pagination"></ul>
</form>

<script type="text/javascript">
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    var limit = 5;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 3,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#maxPageItem').val(limit);
                    $('#page').val(page);
                    $('#sortName').val('createddate');
                    $('#sortBy').val('desc');
                    $('#type').val('list');
                    $('#formSubmit').submit();
                }
            }
        });
    });

    $("#btnDelete").click(function() {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        if (ids.length != 0) {
            data['ids'] = ids;
            deleteNew(data);
        }
    });

    function deleteNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                <c:url var="NewsURL" value="/admin-news">
                <c:param name="type" value="list"/>
                <c:param name="page" value="1"/>
                <c:param name="maxPageItem" value="3"/>
                <c:param name="sortName" value="createddate"/>
                <c:param name="sortBy" value="desc"/>
                <c:param name="message" value="delete_success"/>
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
