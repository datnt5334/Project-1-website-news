package com.bknews.service;

import com.bknews.model.CategoriesModel;
import com.bknews.service.impl.CategoryService;

import java.util.List;

public interface ICategoryService {
    List<CategoriesModel> findAll();
    CategoriesModel findOne(Long id);
}
