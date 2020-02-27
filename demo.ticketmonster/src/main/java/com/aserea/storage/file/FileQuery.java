package com.aserea.storage.file;

import com.aserea.storage.Query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.naming.OperationNotSupportedException;

public abstract class FileQuery implements Query {

    protected FileStorageConnection connection;
    protected String queryAsString;
    protected Object[] params;

    public FileQuery(FileStorageConnection connection, String queryAsString, Object[] params) {
        this.connection = connection;
        this.queryAsString = queryAsString;
        this.params = params;
    }

    public static FileQuery getInstance(FileStorageConnection connection, String queryAsString, Object[] params) {
        if (queryAsString.startsWith("r")) {
            return new ReadFileQuery(connection, queryAsString, params);
        } else if (queryAsString.startsWith("w")) {
            return new WriteFileQuery(connection, queryAsString, params);
        } else {
            // should be checked at construction time - violates object contract
            throw new IllegalArgumentException("Not a valid query: " + queryAsString);
        }
    }
}
