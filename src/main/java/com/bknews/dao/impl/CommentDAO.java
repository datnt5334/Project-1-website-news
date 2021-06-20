package com.bknews.dao.impl;

import com.bknews.dao.ICommentDAO;
import com.bknews.loading.Loadable;
import com.bknews.mapper.CommentMapper;
import com.bknews.model.CommentModel;

import java.util.List;

public class CommentDAO extends AbstractDAO<CommentModel> implements ICommentDAO {

    @Override
    public Long save(CommentModel commentModel) {
        StringBuilder sql = new StringBuilder("insert into comment(content, user_id,");
        sql.append(" news_id, createddate, createdby)");
        sql.append(" values(?,?,?,?,?)");
        return insert(sql.toString(), commentModel.getContent(), commentModel.getUserId(), commentModel.getNewsId(),
                commentModel.getCreatedDate(), commentModel.getCreatedBy());
    }

    @Override
    public void update(CommentModel updateComment) {
        StringBuilder sql = new StringBuilder("UPDATE comment SET content = ?, modifieddate = ?,");
        sql.append(" modifiedby = ? WHERE id = ?");
        update(sql.toString(), updateComment.getContent(), updateComment.getModifiedDate(),
                updateComment.getModifiedBy(), updateComment.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from comment where id = ?";
        update(sql, id);
    }

    @Override
    public CommentModel findOne(Long id) {
        String sql = "select * from comment where id = ?";
        List<CommentModel> listComments = query(sql, new CommentMapper(), id);
        return listComments.isEmpty() ? null : listComments.get(0);
    }

    @Override
    public List<CommentModel> findByNewsId(Long newsId, Loadable loadable) {
        StringBuilder sql = new StringBuilder("select * from comment as c");
        sql.append(" inner join user as u on u.id = c.user_id");
        sql.append(" where news_id = ?");
        if (loadable.getSorter().getSortName() != null && loadable.getSorter().getSortBy() != null) {
            sql.append(" order by c."+loadable.getSorter().getSortName()+" "+loadable.getSorter().getSortBy()+"");
        }
        if (loadable.getOffset() != null && loadable.getLimit() != null) {
            sql.append(" limit "+loadable.getOffset()+", "+loadable.getLimit()+"");
        }
        return query(sql.toString(), new CommentMapper(), newsId);
    }

    @Override
    public List<CommentModel> findByNewsIdToRemove(Long newsId) {
        String sql = "select * from comment as c where news_id = ?";
        return query(sql, new CommentMapper(), newsId);
    }

    @Override
    public Integer getTotalItemByNewsId(Long newsId) {
        String sql = "select count(*) from comment where news_id = ?";
        return count(sql, newsId);
    }

    @Override
    public Integer getTotalItem() {
        String sql = "select count(*) from comment";
        return count(sql);
    }

}
