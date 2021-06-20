package com.bknews.controller.web;

import com.bknews.constant.SystemConstant;
import com.bknews.model.NewsModel;
import com.bknews.model.UserModel;
import com.bknews.paging.PageRequest;
import com.bknews.paging.Pageble;
import com.bknews.service.ICategoryService;
import com.bknews.service.INewsService;
import com.bknews.service.IUserService;
import com.bknews.sort.Sorter;
import com.bknews.utils.FromUtil;
import com.bknews.utils.MessageUtil;
import com.bknews.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/dang-ky", "/thoat"})

public class HomeController extends HttpServlet {

    @Inject
    private ICategoryService categoryService;

    @Inject
    private INewsService newsService;

    @Inject
    private IUserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            MessageUtil.showMessage(request);
            RequestDispatcher rd = request.getRequestDispatcher("views/login/login.jsp");
            rd.forward(request, response);
        }
        else if (action != null && action.equals("sign")) {
            MessageUtil.showMessage(request);
            RequestDispatcher rd = request.getRequestDispatcher("views/login/sign.jsp");
            rd.forward(request, response);
        }
        else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeValue(request, "USERMODEL");
            response.sendRedirect(request.getContextPath()+"/trang-chu");
        }
        else {
            NewsModel model1 = new NewsModel();
            NewsModel model2 = new NewsModel();
            model1.setLimit(7);
            model1.setListResult(newsService.findRandom(model1.getLimit()));
            model2.setPage(1);
            model2.setMaxPageItem(5);
            model2.setSortName("createddate");
            model2.setSortBy("desc");
            Pageble pageble = new PageRequest(model2.getPage(), model2.getMaxPageItem(),
                    new Sorter(model2.getSortName(), model2.getSortBy()));
            model2.setListResult(newsService.findAll(pageble));
            request.setAttribute("model1", model1);
            request.setAttribute("model2", model2);
            request.setAttribute("categories", categoryService.findAll());
            RequestDispatcher rd = request.getRequestDispatcher("views/web/home.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel model = FromUtil.toModel(UserModel.class, request);
            model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword());
            if (model != null) {
                SessionUtil.getInstance().putValue(request, "USERMODEL", model);
                if (model.getRole().getCode().equals("USER")) {
                    response.sendRedirect(request.getContextPath()+"/trang-chu");
                } else if (model.getRole().getCode().equals("ADMIN")) {
                    response.sendRedirect(request.getContextPath()+"/admin-home");
                }
            } else {
                response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login"
                +"&message=username_password_invalid");
            }
        } else if (action != null && action.equals("sign")) {
            UserModel model = FromUtil.toModel(UserModel.class, request);
            Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
                    new Sorter(model.getSortName(), model.getSortBy()));
            boolean flag = true;
            model.setListResult(userService.findAll(pageble));
            for (UserModel user:model.getListResult()) {
                if (user.getUserName().equals(model.getUserName())) {
                    flag = false;
                }
            }
            if (flag) {
                userService.save(model);
                response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=sign_in_success");
            } else {
                response.sendRedirect(request.getContextPath()+"/dang-ky?action=sign&message=username_invalid");
            }
        }
    }
}
