package com.bknews.controller.web;

import com.bknews.loading.LoadRequest;
import com.bknews.loading.Loadable;
import com.bknews.model.CommentModel;
import com.bknews.model.NewsModel;
import com.bknews.service.ICategoryService;
import com.bknews.service.ICommentService;
import com.bknews.service.INewsService;
import com.bknews.sort.Sorter;
import com.bknews.utils.FromUtil;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/chi-tiet"})

public class DetailController extends HttpServlet {

    @Inject
    private INewsService newsService;

    @Inject
    private ICategoryService categoryService;

    @Inject
    private ICommentService commentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsModel model1 = FromUtil.toModel(NewsModel.class, request);
        NewsModel model2 = new NewsModel();
        CommentModel comment = new CommentModel();
        String newsId = request.getParameter("id");
        Long newId = Long.parseLong(newsId);
        comment.setOffset(0);
        comment.setLimit(3);
        Sorter sorter = new Sorter("createddate", "desc");
        Loadable loadable = new LoadRequest(comment.getOffset(), comment.getLimit(), sorter);
        comment.setListResult(commentService.findByNewsId(newId, loadable));
        comment.setTotalItem(commentService.getTotalItemByNewsId(newId));
        model2.setListResult(newsService.findRandom(5));
        if (model1.getId() != null) {
            model1 = newsService.findOne(model1.getId());
        }
        request.setAttribute("model1", model1);
        request.setAttribute("model2", model2);
        request.setAttribute("comment", comment);
        request.setAttribute("categories", categoryService.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("views/detail/detail.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
