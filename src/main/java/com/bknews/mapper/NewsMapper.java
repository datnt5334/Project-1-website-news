package com.bknews.mapper;

import com.bknews.model.NewsModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper implements RowMapper<NewsModel> {

    @Override
    public NewsModel mapRow(ResultSet resultSet) {
        try{
            NewsModel news = new NewsModel();
            news.setId(resultSet.getLong("id"));
            news.setTitle(resultSet.getString("title"));
            news.setThumbnail(resultSet.getString("thumbnail"));
            news.setShortDescription(resultSet.getString("shortdescription"));
            news.setContent(resultSet.getString("content"));
            news.setCategoryId(resultSet.getLong("categoryid"));
            news.setCreatedDate(resultSet.getTimestamp("createddate"));
            news.setCreatedBy(resultSet.getString("createdby"));
            if (resultSet.getTimestamp("modifieddate") != null) {
                news.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            }
            if (resultSet.getString("modifiedby") != null) {
                news.setModifiedBy(resultSet.getString("modifiedby"));
            }
            return news;
        } catch (SQLException e) {
            return null;
        }
    }
}
