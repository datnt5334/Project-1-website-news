package com.bknews.service;


import com.bknews.loading.Loadable;
import com.bknews.model.CommentModel;

import java.util.List;

public interface ICommentService {
    CommentModel save(CommentModel commentModel);
    CommentModel update(CommentModel commentModel);
    void delete(Long[] ids);
    List<CommentModel> findByNewsId(Long newsId, Loadable loadable);
    Integer getTotalItemByNewsId(Long newsId);
    Integer getTotalItem();
    List<CommentModel> findByNewsIdToRemove(Long newsId);
}
