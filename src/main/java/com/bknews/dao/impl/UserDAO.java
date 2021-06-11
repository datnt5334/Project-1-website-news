package com.bknews.dao.impl;

import com.bknews.dao.IUserDAO;
import com.bknews.mapper.NewsMapper;
import com.bknews.mapper.UserMapper;
import com.bknews.model.NewsModel;
import com.bknews.model.UserModel;
import com.bknews.paging.Pageble;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password) {
        StringBuilder sql = new StringBuilder("select * from user as u");
        sql.append(" inner join role as r on r.id = u.roleid");
        sql.append(" where username = ? and password = ?");
        List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Long save(UserModel userModel) {
        StringBuilder sql = new StringBuilder("insert into user(username, password,");
        sql.append(" fullname, roleid, createddate)");
        sql.append(" values(?,?,?,?,?)");
        return insert(sql.toString(), userModel.getUserName(), userModel.getPassword(), userModel.getFullName(),
                      userModel.getRoleId(), userModel.getCreatedDate());
    }

    @Override
    public UserModel findOne(Long id) {
        String sql = "select * from user where id = ?";
        List<UserModel> listNews = query(sql, new UserMapper(), id);
        return listNews.isEmpty() ? null : listNews.get(0);
    }

    @Override
    public List<UserModel> findAll(Pageble pageble) {
        StringBuilder sql =  new StringBuilder("select * from user");
        if (pageble.getSorter().getSortName() != null && pageble.getSorter().getSortBy() != null) {
            sql.append(" order by "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
        }
        if (pageble.getOffSet() != null && pageble.getLimit() != null){
            sql.append(" limit "+pageble.getOffSet()+", "+pageble.getLimit()+"");
        }
        return query(sql.toString(), new UserMapper());
    }

    @Override
    public Integer getTotalItem() {
        String sql = "select count(*) from user";
        return count(sql);
    }

}
