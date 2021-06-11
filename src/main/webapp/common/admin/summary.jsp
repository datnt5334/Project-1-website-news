<%--
  Created by IntelliJ IDEA.
  User: datnguyen
  Date: 5/14/21
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/tagLib.jsp"%>

<div class="summary">
    <div class="summary-info">
        <div class="summary-single">
            <span class="fas fa-user-alt"></span>
            <div>
                <h5 id="counter" data-target="1960">0</h5>
                <small>Tài khoản</small>
            </div>
        </div>
        <div class="summary-single">
            <span class="fas fa-file-alt"></span>
            <div>
                <h5 id="counter" data-target="5000">0</h5>
                <small>Bài viết</small>
            </div>
        </div>
        <div class="summary-single">
            <span class="fas fa-comment-alt"></span>
            <div>
                <h5 id="counter" data-target="10000">0</h5>
                <small>Bình luận</small>
            </div>
        </div>
    </div>
</div>