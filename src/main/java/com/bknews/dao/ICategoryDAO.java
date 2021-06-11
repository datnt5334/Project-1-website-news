package com.bknews.dao;

import com.bknews.model.CategoriesModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO<CategoriesModel> {
    List<CategoriesModel> findAll();
    CategoriesModel findOne(Long id);
    CategoriesModel findOneByCode(String code);
}
