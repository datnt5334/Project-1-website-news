package com.bknews.service;

import com.bknews.model.NewsModel;
import com.bknews.paging.Pageble;

import java.util.List;

public interface INewsService {
    List<NewsModel> findByCategoryId(Long categoryId, Integer offset, Integer limit);
    NewsModel save(NewsModel newsModel);
    NewsModel update(NewsModel updateNews);
    void delete(Long[] ids);
    List<NewsModel> findAll(Pageble pageble);
    Integer getTotalItem();
    NewsModel findOne(Long id);
    List<NewsModel> findRandom(Integer limit);
    Integer getTotalByCategoryId(Long categoryId);
}
