package com.bknews.service.impl;

import com.bknews.dao.ICategoryDAO;
import com.bknews.model.CategoriesModel;
import com.bknews.service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {

    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<CategoriesModel> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public CategoriesModel findOne(Long id) {
        return categoryDAO.findOne(id);
    }

}
