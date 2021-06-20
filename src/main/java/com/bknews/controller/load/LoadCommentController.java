package com.bknews.controller.load;

import com.bknews.loading.LoadRequest;
import com.bknews.loading.Loadable;
import com.bknews.model.CommentModel;
import com.bknews.service.ICommentService;
import com.bknews.sort.Sorter;
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

@WebServlet(urlPatterns = {"/load-comment"})

public class LoadCommentController extends HttpServlet {

    @Inject
    private ICommentService commentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CommentModel model = FromUtil.toModel(CommentModel.class, request);
        String newsId = request.getParameter("id");
        Long newId = Long.parseLong(newsId);
        Long roleId = null;
        String userName = request.getParameter("username");
        String roleid = request.getParameter("roleId");
        if (! roleid.equals("")) {
            roleId = Long.parseLong(roleid);
        }
        Sorter sorter = new Sorter("createddate", "desc");
        Loadable loadable = new LoadRequest(model.getOffset(), model.getLimit(), sorter);
        List<CommentModel> listComments = commentService.findByNewsId(newId, loadable);
        PrintWriter out = response.getWriter();
        for (CommentModel o: listComments) {
            out.println("<div class=\"comment-left load\">\n" +
                    "<div class=\"profileImage\" id=\"profile_"+o.getId()+"\">"+o.getUser().getFullName()+"</div>\n" +
                    "</div>\n" +
                    "<div class=\"comment-right\">\n" +
                    "<p>\n" +
                    "<strong>\n" +
                    "<span>"+o.getUser().getFullName()+"</span>\n" +
                    "</strong>\n" +
                    " "+o.getContent()+"\n" +
                    "</p>\n");
            if (roleId != null && roleId.equals(1L)) {
                out.println("<a href=\"#\" class=\"remove-item\" id=\""+o.getId()+"\" data-toggle=\"tooltip\" title='Xóa bình'>\n" +
                        "<span class=\"fas fa-times\"></span>\n" +
                        "</a>");
            }
            if (roleId != null && roleId.equals(2L) && userName.equals(o.getUser().getUserName())) {
                out.println("<a href=\"#\" class=\"remove-item\" id=\""+o.getId()+"\" data-toggle=\"tooltip\" title='Xóa bình luận'>\n" +
                        "<span class=\"fas fa-times\"></span>\n" +
                        "</a>");
            }
            out.println("</div>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
