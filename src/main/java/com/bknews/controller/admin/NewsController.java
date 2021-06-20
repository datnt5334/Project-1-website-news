package com.bknews.controller.admin;

import com.bknews.constant.SystemConstant;
import com.bknews.dao.ICategoryDAO;
import com.bknews.model.NewsModel;
import com.bknews.paging.PageRequest;
import com.bknews.paging.Pageble;
import com.bknews.service.ICategoryService;
import com.bknews.service.ICommentService;
import com.bknews.service.INewsService;
import com.bknews.service.IUserService;
import com.bknews.sort.Sorter;
import com.bknews.utils.FromUtil;
import com.bknews.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/admin-news"})

public class NewsController extends HttpServlet {

    @Inject
    private INewsService newsService;

    @Inject
    private ICategoryService categoryService;

    @Inject
    private IUserService userService;

    @Inject
    private ICommentService commentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsModel model = FromUtil.toModel(NewsModel.class, request);
        String view = "";
        if (model.getType().equals(SystemConstant.LIST)) {
            MessageUtil.showMessage(request);
            Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
            model.setListResult(newsService.findAll(pageble));
            model.setTotalItem(newsService.getTotalItem());
            model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            request.setAttribute("categories", categoryService.findAll());
            view = "views/admin/news/list.jsp";
        } else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                model = newsService.findOne(model.getId());
            }
            request.setAttribute("categories", categoryService.findAll());
            view = "views/admin/news/edit.jsp";
        }
        Integer totalNews = newsService.getTotalItem();
        Integer totalUser = userService.getTotalItem();
        Integer totalComment = commentService.getTotalItem();
        request.setAttribute("totalNews", totalNews);
        request.setAttribute("totalUser", totalUser);
        request.setAttribute("totalComment", totalComment);
        request.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
