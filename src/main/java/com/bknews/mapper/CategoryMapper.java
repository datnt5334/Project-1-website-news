package com.bknews.mapper;

import com.bknews.model.CategoriesModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<CategoriesModel> {

    @Override
    public CategoriesModel mapRow(ResultSet resultSet) {
        try{
            CategoriesModel category = new CategoriesModel();
            category.setId(resultSet.getLong("id"));
            category.setName(resultSet.getString("name"));
            category.setCode(resultSet.getString("code"));
            return category;
        } catch (SQLException e) {
            return null;
        }
    }
}
