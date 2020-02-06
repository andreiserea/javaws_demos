package com.aserea.storage;

public interface StorageEngineConnection {

    Query createQuery(String queryAsString, Object ... args);
}
