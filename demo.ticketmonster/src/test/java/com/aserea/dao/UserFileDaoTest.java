package com.aserea.dao;

import com.aserea.exceptions.EntityNotFoundException;
import com.aserea.model.User;
import com.aserea.storage.Query;
import com.aserea.storage.StorageEngineConnection;
import com.aserea.storage.file.FileQuery;
import com.aserea.storage.file.FileStorageConnection;
import com.aserea.storage.file.FileStorageEngine;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UserFileDaoTest {

    private static final User u1 = new User(1, "andrei", "aserea@fitbit.com", "Andrei", "Serea", "Home");
    private static final User u2 = new User(2, "ion", "ion@fitbit.com", "Ion", "Popescu", "Home");

    @Test
    /**
     * Good Test only tests UserFileDao.delete method in isolation.
     */
    public void test_deleteUser() {
        UserFileDao userDao = new UserFileDao(new FileStorageConnectionTest("."));

        userDao.delete(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void test_getUser() {
        UserFileDao userDao = new UserFileDao(new FileStorageConnectionTest("."));

        User u = userDao.get(2);
        Assert.assertEquals(u2, u);
        userDao.get(1);
    }

    /**
     *
     * Another design problem surfaces here: it would have been easier to implement StorageEngineConnection but we can't
     * because UserFileDao depends on FileStorageConnection and not on the interface
     */
    private static class FileStorageConnectionTest extends FileStorageConnection {

        public FileStorageConnectionTest(String dirPath) {
            super(dirPath);
        }

        @Override
        public Query createQuery(String queryAsString, Object... args) {
            Assert.assertTrue(queryAsString.startsWith("d/") || queryAsString.startsWith("r/"));
            if (queryAsString.startsWith("d/")) {
                Assert.assertTrue(args[0].equals(1));
            }
            return new FileQueryGetTest();
        }
    }

    private static class FileQueryGetTest implements  Query {

        @Override
        public byte[] execute() {
            return (u1.toString() + "\n" + u2.toString() + "\n" + FileQuery.TOMBSTONE + "\n" + 1).getBytes();
        }
    }

}
