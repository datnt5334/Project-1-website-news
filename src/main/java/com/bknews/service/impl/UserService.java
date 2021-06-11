package com.bknews.service.impl;

import com.bknews.dao.IUserDAO;
import com.bknews.model.UserModel;
import com.bknews.paging.Pageble;
import com.bknews.service.IUserService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class UserService implements IUserService {

    @Inject
    private IUserDAO userDAO;

    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password) {
        return userDAO.findByUserNameAndPasswordAndStatus(userName, password);
    }

    @Override
    public UserModel save(UserModel userModel) {
        userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        userModel.setRoleId(2l);
        Long userId = userDAO.save(userModel);
        return userDAO.findOne(userId);
    }

    @Override
    public UserModel findOne(Long id) {
        return userDAO.findOne(id);
    }

    @Override
    public List<UserModel> findAll(Pageble pageble) {
        return userDAO.findAll(pageble);
    }

    @Override
    public Integer getTotalItem() {
        return userDAO.getTotalItem();
    }
}
