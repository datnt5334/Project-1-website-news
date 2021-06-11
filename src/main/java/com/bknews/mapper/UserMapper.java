package com.bknews.mapper;

import com.bknews.model.RoleModel;
import com.bknews.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet resultSet) {
        try{
            UserModel user = new UserModel();
            user.setUserName(resultSet.getString("username"));
            user.setFullName(resultSet.getString("fullname"));
            user.setPassword(resultSet.getString("password"));
            user.setCreatedDate(resultSet.getTimestamp("createddate"));
            try {
                RoleModel role = new RoleModel();
                role.setCode(resultSet.getString("code"));
                role.setName(resultSet.getString("name"));
                user.setRole(role);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            return user;
        } catch (SQLException e) {
            return null;
        }
    }
}
