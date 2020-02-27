package com.aserea.storage.file;

import com.aserea.storage.Criteria;
import com.aserea.storage.Query;
import com.aserea.storage.StorageEngineConnection;

public class FileStorageConnection implements StorageEngineConnection {

    private final String dirPath;

    public FileStorageConnection(String dirPath) {
        this.dirPath = dirPath;
    }

    public String getDirPath() {
        return dirPath;
    }

    @Override
    public Query createQuery(String queryAsString, Object ... args) {
        return FileQuery.getInstance(this, queryAsString, args);
    }
}
