package com.bknews.dao;

import com.bknews.loading.Loadable;
import com.bknews.model.CommentModel;

import java.util.List;

public interface ICommentDAO extends GenericDAO<CommentModel> {
    Long save(CommentModel commentModel);
    void update(CommentModel commentModel);
    void delete(Long id);
    CommentModel findOne(Long id);
    List<CommentModel> findByNewsId(Long newsId, Loadable loadable);
    List<CommentModel> findByNewsIdToRemove(Long newsId);
    Integer getTotalItemByNewsId(Long newsId);
    Integer getTotalItem();
}
