package com.bknews.controller.load;

import com.bknews.model.NewsModel;
import com.bknews.service.ICategoryService;
import com.bknews.service.INewsService;
import com.bknews.utils.FromUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/load-category"})

public class LoadCategoryController extends HttpServlet {

    @Inject
    private ICategoryService categoryService;

    @Inject
    private INewsService newsService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        NewsModel model = FromUtil.toModel(NewsModel.class, request);
        List<NewsModel> listNews = newsService.findByCategoryId(model.getCategoryId(),model.getOffset(),model.getLimit());
        PrintWriter out = response.getWriter();
        for (NewsModel o: listNews) {
            out.println("<div id=\"content\">\n" +
                    "<article class=\"load\">\n" +
                    "<div>\n" +
                    "<h2>"+o.getTitle()+"</h2>\n" +
                    "\n" +
                    "<p>"+o.getShortDescription()+"</p>\n" +
                    "<a href=\"chi-tiet?id="+o.getId()+"\">Đọc thêm<span>>></span></a>\n" +
                    "</div>\n" +
                    "<img src=\""+o.getThumbnail()+"\">\n" +
                    "\n" +
                    "</article>\n" +
                    "</div>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
