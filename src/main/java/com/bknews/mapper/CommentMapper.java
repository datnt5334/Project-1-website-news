package com.bknews.mapper;

import com.bknews.model.CommentModel;
import com.bknews.model.RoleModel;
import com.bknews.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<CommentModel> {

    @Override
    public CommentModel mapRow(ResultSet resultSet) {
        try{
            CommentModel comment = new CommentModel();
            comment.setId(resultSet.getLong("c.id"));
            comment.setContent(resultSet.getString("content"));
            comment.setUserId(resultSet.getLong("user_id"));
            comment.setNewsId(resultSet.getLong("news_id"));
            comment.setCreatedDate(resultSet.getTimestamp("createddate"));
            comment.setCreatedBy(resultSet.getString("createdby"));
            if (resultSet.getTimestamp("modifieddate") != null) {
                comment.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            }
            if (resultSet.getString("modifiedby") != null) {
                comment.setModifiedBy(resultSet.getString("modifiedby"));
            }
            try {
                UserModel user = new UserModel();
                user.setId(resultSet.getLong("u.id"));
                user.setUserName(resultSet.getString("username"));
                user.setFullName(resultSet.getString("fullname"));
                comment.setUser(user);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            return comment;
        } catch (SQLException e) {
            return null;
        }
    }
}
