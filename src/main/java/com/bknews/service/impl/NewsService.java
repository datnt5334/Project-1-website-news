package com.bknews.service.impl;

import com.bknews.dao.ICategoryDAO;
import com.bknews.dao.ICommentDAO;
import com.bknews.dao.INewsDAO;
import com.bknews.loading.LoadRequest;
import com.bknews.loading.Loadable;
import com.bknews.model.CategoriesModel;
import com.bknews.model.CommentModel;
import com.bknews.model.NewsModel;
import com.bknews.paging.Pageble;
import com.bknews.service.ICommentService;
import com.bknews.service.INewsService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class NewsService implements INewsService {

    @Inject
    private INewsDAO newsDAO;

    @Inject
    private ICategoryDAO categoryDAO;

    @Inject
    private ICommentDAO commentDAO;

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId, Loadable loadable) {
        return newsDAO.findByCategoryId(categoryId, loadable);
    }

    @Override
    public NewsModel save(NewsModel newsModel) {
        newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        CategoriesModel category = categoryDAO.findOneByCode(newsModel.getCategoryCode());
        newsModel.setCategoryId(category.getId());
        Long newId = newsDAO.save(newsModel);
        return newsDAO.findOne(newId);
    }

    @Override
    public NewsModel update(NewsModel updateNews) {
        NewsModel oldNews = newsDAO.findOne(updateNews.getId());
        updateNews.setCreatedDate(oldNews.getCreatedDate());
        updateNews.setCreatedBy(oldNews.getCreatedBy());
        updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        CategoriesModel category = categoryDAO.findOneByCode(updateNews.getCategoryCode());
        updateNews.setCategoryId(category.getId());
        newsDAO.update(updateNews);
        return newsDAO.findOne(updateNews.getId());
    }

    @Override
    public void delete(Long[] ids) {
        for (long id: ids) {
            List<CommentModel> listComments = commentDAO.findByNewsIdToRemove(id);
            for (CommentModel comment: listComments) {
                commentDAO.delete(comment.getId());
            }
            newsDAO.delete(id);
        }
    }

    @Override
    public List<NewsModel> findAll(Pageble pageble) {
        return newsDAO.findAll(pageble);
    }

    @Override
    public Integer getTotalItem() {
        return newsDAO.getTotalItem();
    }

    @Override
    public NewsModel findOne(Long id) {
        NewsModel newsModel = newsDAO.findOne(id);
        CategoriesModel categoriesModel  = categoryDAO.findOne(newsModel.getCategoryId());
        newsModel.setCategoryCode(categoriesModel.getCode());
        return newsModel;
    }

    @Override
    public List<NewsModel> findRandom(Integer limit) {
        return newsDAO.findRandom(limit);
    }

    @Override
    public Integer getTotalByCategoryId(Long categoryId) {
        return newsDAO.getTotalByCategoryId(categoryId);
    }
}
