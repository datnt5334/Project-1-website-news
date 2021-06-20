package com.bknews.controller.web;

import com.bknews.loading.LoadRequest;
import com.bknews.loading.Loadable;
import com.bknews.model.NewsModel;
import com.bknews.service.ICategoryService;
import com.bknews.service.INewsService;
import com.bknews.sort.Sorter;
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
        NewsModel model = FromUtil.toModel(NewsModel.class, request);
        Sorter sorter = new Sorter("createddate", "desc");
        Loadable loadable = new LoadRequest(model.getOffset(), model.getLimit(), sorter);
        model.setListResult(newsService.findByCategoryId(model.getCategoryId(), loadable));
        model.setTotalItem(newsService.getTotalByCategoryId(model.getCategoryId()));
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("category", categoryService.findOne(model.getCategoryId()));
        request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher("views/category/category.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
