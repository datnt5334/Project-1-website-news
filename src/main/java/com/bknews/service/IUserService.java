package com.bknews.service;

import com.bknews.model.UserModel;
import com.bknews.paging.Pageble;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface IUserService {
    UserModel findByUserNameAndPasswordAndStatus(String userName, String password);
    UserModel save(UserModel userModel);
    UserModel findOne(Long id);
    List<UserModel> findAll(Pageble pageble);
    Integer getTotalItem();
}
