package com.aserea;

import com.aserea.dao.UserFileDao;
import com.aserea.model.User;
import com.aserea.storage.file.FileStorageEngine;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        User user1 = new User(1, "user1", "a@a.com", "andrei", "serea", "bucuresti");
        User user2 = new User(2, "user2", "b@b.com", "ion", "mihai", "bucuresti");
        FileStorageEngine fileStorageEngine = new FileStorageEngine("/tmp");
        UserFileDao userFileDao = new UserFileDao(fileStorageEngine.getConnection());
        userFileDao.create(user1);
        userFileDao.create(user2);
    }
}
