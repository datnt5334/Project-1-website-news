package com.bknews.service.impl;

import com.bknews.dao.ICommentDAO;
import com.bknews.loading.Loadable;
import com.bknews.model.CommentModel;
import com.bknews.service.ICommentService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class CommentService implements ICommentService {

    @Inject
    private ICommentDAO commentDAO;

    @Override
    public CommentModel save(CommentModel commentModel) {
        commentModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long commentId = commentDAO.save(commentModel);
        return commentDAO.findOne(commentId);
    }

    @Override
    public CommentModel update(CommentModel commentModel) {
        commentModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        commentDAO.update(commentModel);
        return commentDAO.findOne(commentModel.getId());
    }

    @Override
    public void delete(Long[] ids) {
        for(long id: ids) {
            commentDAO.delete(id);
        }
    }

    @Override
    public List<CommentModel> findByNewsId(Long newsId, Loadable loadable) {
        return commentDAO.findByNewsId(newsId, loadable);
    }

    @Override
    public Integer getTotalItemByNewsId(Long newsId) {
        return commentDAO.getTotalItemByNewsId(newsId);
    }

    @Override
    public Integer getTotalItem() {
        return commentDAO.getTotalItem();
    }

    @Override
    public List<CommentModel> findByNewsIdToRemove(Long newsId) {
        return commentDAO.findByNewsIdToRemove(newsId);
    }
}
