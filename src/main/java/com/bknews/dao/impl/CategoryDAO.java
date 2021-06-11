package com.bknews.dao.impl;

import com.bknews.dao.ICategoryDAO;
import com.bknews.mapper.CategoryMapper;
import com.bknews.mapper.NewsMapper;
import com.bknews.model.CategoriesModel;
import com.bknews.model.NewsModel;

import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoriesModel> implements ICategoryDAO {

    @Override
    public List<CategoriesModel> findAll() {
        String sql = "select * from categories";
        return query(sql, new CategoryMapper());
    }

    @Override
    public CategoriesModel findOne(Long id) {
        String sql = "select * from categories where id = ?";
        List<CategoriesModel> listCategories = query(sql, new CategoryMapper(), id);
        return listCategories.isEmpty() ? null : listCategories.get(0);
    }

    @Override
    public CategoriesModel findOneByCode(String code) {
        String sql = "select * from categories where code = ?";
        List<CategoriesModel> listCategories = query(sql, new CategoryMapper(), code);
        return listCategories.isEmpty() ? null : listCategories.get(0);
    }
}
