package com.aserea.dao;

import com.aserea.model.User;
import com.aserea.storage.file.FileStorageEngine;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UserFileDaoTest {

    @Test
    public void test_deleteUser() {
        List<User> users = Arrays.asList(
                new User(1, "andrei", "aserea@fitbit.com", "Andrei", "Serea", "Home"),
                new User(2, "ion", "ion@fitbit.com", "Ion", "Popescu", "Home")
        );
        FileStorageEngine fileStorageEngine = new FileStorageEngine(".");
        UserFileDao userDao = new UserFileDao(fileStorageEngine.getConnection());
        users.forEach(userDao::create);
        userDao.delete(1);
        userDao.get(2);
        userDao.get(1);
    }
}
