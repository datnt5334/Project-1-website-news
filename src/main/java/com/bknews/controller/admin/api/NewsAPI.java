package com.bknews.controller.admin.api;

import com.bknews.model.NewsModel;
import com.bknews.model.UserModel;
import com.bknews.service.INewsService;
import com.bknews.utils.HttpUtil;
import com.bknews.utils.SessionUtil;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-news"})

public class NewsAPI extends HttpServlet {

    @Inject
    private INewsService newsService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
        newsModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        newsModel = newsService.save(newsModel);
        mapper.writeValue(response.getOutputStream(), newsModel);
    }
    protected void doPut(@org.jetbrains.annotations.NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewsModel updateNews = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
        updateNews.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        updateNews = newsService.update(updateNews);
        mapper.writeValue(response.getOutputStream(), updateNews);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
        newsService.delete(newsModel.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

}
