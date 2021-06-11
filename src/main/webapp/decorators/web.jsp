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
    <title><dec:title default="trang chu"/></title>

    <meta charset="UTF-8">
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value='/template/web/style.css'/>">

    <!-- google font -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- font-awesome -->
    <script src="https://kit.fontawesome.com/e7580bebbc.js" crossorigin="anonymous"></script>

    <!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body>

<!--  header -->
    <%@ include file="/common/web/header.jsp" %>
<!--  header -->

<!-- banner -->
    <%@ include file="/common/web/banner.jsp" %>
<!-- banner -->

<hr>

<!-- main -->
    <dec:body/>
<!-- main -->

<!-- footer -->
     <%@ include file="/common/web/footer.jsp" %>
<!-- footer -->

<button id="topBtn"><i class="fas fa-arrow-up"></i></button>

<script type="text/javascript" src="<c:url value='/template/web/event.js'/>"></script>

</body>
</html>
