package com.bknews.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
    public static void showMessage(HttpServletRequest request) {
        if (request.getParameter("message") != null) {
            String messageResponse = "";
            String alertResponse = "";
            String message = request.getParameter("message");
            if (message.equals("not_permission")) {
                messageResponse = "Không được phép truy cập !";
                alertResponse = "danger";
            } else if (message.equals("not_login")) {
                messageResponse = "Bạn chưa đăng nhập vào tài khoản admin!";
                alertResponse = "warning";
            } else if (message.equals("username_password_invalid")) {
                messageResponse = "Tài khoản và mật khẩu không chính xác !";
                alertResponse = "danger";
            } else if (message.equals("insert_success")) {
                messageResponse = "Thêm thành công !";
                alertResponse = "success";
            } else if (message.equals("update_success")) {
                messageResponse = "Cập nhật thành công !";
                alertResponse = "success";
            } else  if (message.equals("delete_success")) {
                messageResponse = "Xóa thành công !";
                alertResponse = "success";
            } else  if (message.equals("username_invalid")) {
                messageResponse = "Tên tài khoản đã tồn tại !";
                alertResponse = "warning";
            }
            request.setAttribute("message", messageResponse);
            request.setAttribute("alert", alertResponse);
        }
    }
}
