package com.aserea.dao;

import com.aserea.exceptions.EntityNotFoundException;
import com.aserea.exceptions.EntityNotUniqueException;
import com.aserea.model.User;
import com.aserea.storage.Query;
import com.aserea.storage.file.FileStorageConnection;

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
        List<User> user = Arrays.stream(new String(bytes).split("\n")).map(User::fromString).filter(
                u -> u.getId() == id).collect(
                Collectors.toList());
        if (user.size() == 1) {
            return user.get(0);
        } else if (user.size() == 0) {
            throw new EntityNotFoundException("User with id " + id + " was not found!");
        } else {
            throw new EntityNotUniqueException("There are " + user.size() + " users with the same id " + id);
        }
    }

    @Override
    public void create(User entity) {
        Query query = connection.createQuery("w/users.txt", entity);
        query.execute();
    }

    @Override
    public void update(User entity) {
        Query query = connection.createQuery("w/users.txt", entity);
        query.execute();
    }

    @Override
    public void delete(Integer id) {

    }
}
