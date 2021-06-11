package com.bknews.controller.web;

import com.bknews.model.NewsModel;
import com.bknews.service.ICategoryService;
import com.bknews.service.INewsService;
import com.bknews.utils.FromUtil;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/the-loai"})

public class CategoryController extends HttpServlet {

    @Inject
    private INewsService newsService;

    @Inject
    private ICategoryService categoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsModel model1 = FromUtil.toModel(NewsModel.class, request);
        NewsModel model2 = FromUtil.toModel(NewsModel.class, request);
        model1.setOffset(0);
        model1.setLimit(6);
        model1.setListResult(newsService.findByCategoryId(model1.getCategoryId(), model1.getOffset(),
                model1.getLimit()));
        model1.setTotalItem(newsService.getTotalByCategoryId(model1.getCategoryId()));
        model2.setOffset(0);
        model2.setLimit(4);
        model2.setListResult(newsService.findByCategoryId(model2.getCategoryId(), model2.getOffset(),
                model2.getLimit()));
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("category", categoryService.findOne(model1.getCategoryId()));
        request.setAttribute("model1", model1);
        request.setAttribute("model2", model2);
        RequestDispatcher rd = request.getRequestDispatcher("views/category/category.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
