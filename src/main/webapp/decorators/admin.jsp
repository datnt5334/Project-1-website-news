<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 5/14/21
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title><dec:title default="trang admin"/></title>

    <meta charset="UTF-8">
    <title></title>
    <!-- bootstrap -->

    <script src="<c:url value='/ckeditor/ckeditor.js'/>"></script>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src="<c:url value="/template/paging/jquery.twbsPagination.js"/>" type="text/javascript"></script>

    <link rel="stylesheet" href="<c:url value='/template/admin/styles.css'/>">

    <!-- google font -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- font-awesome -->
    <script src="https://kit.fontawesome.com/e7580bebbc.js" crossorigin="anonymous"></script>

    <!-- themify icon -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">


</head>
<body>

<input type="checkbox" id="sidebar-toggle">

<!--  siderbar -->
    <%@ include file="/common/admin/sidebar.jsp" %>
<!--  siderbar -->

<div class="main-content">
    <!--  header -->
        <%@ include file="/common/admin/header.jsp" %>
    <!--  header -->
    <main>
        <section class="manager">
            <div class="grid-manager">
                <dec:body/>
            </div>
        </section>
    </main>
</div>

<script type="text/javascript" src="<c:url value='/template/admin/event.js'/>"></script>
</body>
</html>
