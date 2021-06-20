package com.bknews.dao;

import com.bknews.loading.Loadable;
import com.bknews.model.NewsModel;
import com.bknews.paging.Pageble;

import java.util.List;

public interface INewsDAO extends GenericDAO<NewsModel> {
    List<NewsModel> findByCategoryId(Long categoryId, Loadable loadable);
    Long save(NewsModel newsModel);
    void update(NewsModel newsModel);
    void delete(Long id);
    NewsModel findOne(Long id);
    List<NewsModel> findAll(Pageble pageble);
    Integer getTotalItem();
    List<NewsModel> findRandom(Integer limit);
    Integer getTotalByCategoryId(Long categoryId);
}
