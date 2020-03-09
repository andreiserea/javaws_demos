package com.aserea.dao;

import com.aserea.exceptions.EntityNotFoundException;
import com.aserea.exceptions.EntityNotUniqueException;
import com.aserea.model.User;
import com.aserea.storage.Query;
import com.aserea.storage.file.FileQuery;
import com.aserea.storage.file.FileStorageConnection;
import com.aserea.storage.file.FileStorageEngine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserFileDao implements EntityDao<User, Integer> {

    private FileStorageConnection connection;

    public UserFileDao(FileStorageConnection connection) {
        this.connection = connection;
    }

    @Override
    public User get(Integer id) {
        Query query = connection.createQuery("r/users.txt");
        byte[] bytes = query.execute();
        User user = null;
        String[] entries = new String(bytes).split("\n");
        for(int i = 0; i < entries.length; i++) {
            if (entries[i].equals(FileQuery.TOMBSTONE)) {
                if (Integer.valueOf(entries[++i]) == id) {
                    user = null;
                }
            } else {
                User temp = User.fromString(entries[i]);
                user = temp.getId().equals(id) ? temp : user;
            }
        }
        if (user != null) {
            return user;
        } else {
            throw new EntityNotFoundException("User with id " + id + " was not found!");
        }
    }

    @Override
    public void create(User entity) {
        upsert(entity);
    }

    private void upsert(User entity) {
        Query query = connection.createQuery("w/users.txt", entity);
        query.execute();
    }

    @Override
    public void update(User entity) {
        upsert(entity);
    }

    @Override
    public void delete(Integer id) {
        Query query = connection.createQuery("d/users.txt", id);
        query.execute();
    }

}
