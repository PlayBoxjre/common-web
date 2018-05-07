package com.kong.datas.impls.jdbc;

import com.kong.datas.daos.UserDao;
import com.kong.datas.models.User;

import java.util.List;

public class UserHibernateDaoImpl implements UserDao {
    @Override
    public User findUserById(int id) {

        return null;
    }

    @Override
    public User findUserByName(String name) {
        return null;
    }

    @Override
    public List<User> findUsersByTime(long startTime, long endTime) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUserByIds(int... ids) {
        return 0;
    }
}
