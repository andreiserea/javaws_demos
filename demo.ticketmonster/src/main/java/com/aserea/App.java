package com.aserea;

import com.aserea.dao.UserFileDao;
import com.aserea.model.User;
import com.aserea.storage.file.FileStorageEngine;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        List<User> users = Arrays.asList(
                new User(1, "andrei", "aserea@fitbit.com", "Andrei", "Serea", "Home"),
                new User(2, "ion", "ion@fitbit.com", "Ion", "Popescu", "Home")
        );
        FileStorageEngine fileStorageEngine = new FileStorageEngine(".");
        UserFileDao userDao = new UserFileDao(fileStorageEngine.getConnection());
        users.forEach(userDao::create);
    }
}
