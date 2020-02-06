package com.aserea.dao;

import com.aserea.storage.StorageEngine;
import com.aserea.storage.StorageEngineConnection;

public interface EntityDao<T, K> {
    T get(K id);
    void create(T entity);
    void update(T entity);
    void delete(K id);

}
