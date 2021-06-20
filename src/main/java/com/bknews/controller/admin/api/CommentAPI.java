package com.bknews.controller.admin.api;

import com.bknews.model.CommentModel;
import com.bknews.model.UserModel;
import com.bknews.service.ICommentService;
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

@WebServlet(urlPatterns = {"/api-comment"})

public class CommentAPI extends HttpServlet {

    @Inject
    private ICommentService commentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CommentModel commentModel = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
        commentModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        commentModel = commentService.save(commentModel);
        mapper.writeValue(response.getOutputStream(), commentModel);
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CommentModel updateComment = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
        updateComment.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        updateComment = commentService.update(updateComment);
        mapper.writeValue(response.getOutputStream(), updateComment);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CommentModel commentModel = HttpUtil.of(request.getReader()).toModel(CommentModel.class);
        commentService.delete(commentModel.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

}
