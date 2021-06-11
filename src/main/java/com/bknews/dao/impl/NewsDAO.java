package com.bknews.dao.impl;

import com.bknews.dao.INewsDAO;
import com.bknews.mapper.NewsMapper;
import com.bknews.model.NewsModel;
import com.bknews.paging.Pageble;

import java.util.List;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId, Integer offset, Integer limit) {
        StringBuilder sql = new StringBuilder("select * from news");
        sql.append(" where categoryid = ?");
        if (offset != null && limit != null) {
            sql.append(" limit "+offset+", "+limit+"");
        }
        return query(sql.toString(), new NewsMapper(), categoryId);
    }

    @Override
    public Long save(NewsModel newsModel) {
        StringBuilder sql = new StringBuilder("insert into news(title, thumbnail,");
        sql.append(" shortdescription, content, categoryid, createddate, createdby)");
        sql.append(" values(?,?,?,?,?,?,?)");
        return insert(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail() ,newsModel.getShortDescription(),
                newsModel.getContent(), newsModel.getCategoryId(),
                newsModel.getCreatedDate(), newsModel.getCreatedBy());
    }

    @Override
    public void update(NewsModel updateNews) {
        StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
        sql.append(" shortdescription = ?, content = ?, categoryid = ?, createddate = ?,");
        sql.append(" createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
        update(sql.toString(), updateNews.getTitle(), updateNews.getThumbnail(), updateNews.getShortDescription(),
                updateNews.getContent(), updateNews.getCategoryId(),
                updateNews.getCreatedDate(), updateNews.getCreatedBy(), updateNews.getModifiedDate(),
                updateNews.getModifiedBy(), updateNews.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from news where id = ?";
        update(sql, id);
    }

    @Override
    public NewsModel findOne(Long id) {
        String sql = "select * from news where id = ?";
        List<NewsModel> listNews = query(sql, new NewsMapper(), id);
        return listNews.isEmpty() ? null : listNews.get(0);
    }

    @Override
    public List<NewsModel> findAll(Pageble pageble) {
        StringBuilder sql =  new StringBuilder("select * from news");
        if (pageble.getSorter().getSortName() != null && pageble.getSorter().getSortBy() != null) {
            sql.append(" order by "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
        }
        if (pageble.getOffSet() != null && pageble.getLimit() != null){
            sql.append(" limit "+pageble.getOffSet()+", "+pageble.getLimit()+"");
        }
        return query(sql.toString(), new NewsMapper());
    }

    @Override
    public Integer getTotalItem() {
        String sql = "select count(*) from news";
        return count(sql);
    }

    @Override
    public List<NewsModel> findRandom(Integer limit) {
        StringBuilder sql = new StringBuilder("select * from news");
        sql.append(" order by rand() limit "+limit+"");
        return query(sql.toString(), new NewsMapper());
    }

    @Override
    public Integer getTotalByCategoryId(Long categoryId) {
        String sql = "select count(*) from news where categoryid = ?";
        return count(sql, categoryId);
    }

}
