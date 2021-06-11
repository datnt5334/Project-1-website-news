package com.bknews.dao;
import com.bknews.model.UserModel;
import com.bknews.paging.Pageble;

import java.util.List;

public interface IUserDAO extends GenericDAO<UserModel> {
    UserModel findByUserNameAndPasswordAndStatus(String userName, String password);
    Long save(UserModel userModel);
    UserModel findOne(Long id);
    List<UserModel> findAll(Pageble pageble);
    Integer getTotalItem();
}
