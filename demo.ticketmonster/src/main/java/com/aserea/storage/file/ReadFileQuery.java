package com.aserea.storage.file;

import com.aserea.storage.Criteria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileQuery extends FileQuery {
    public ReadFileQuery(FileStorageConnection connection, String queryAsString, Object[] params) {
        super(connection, queryAsString, params);
    }

    public byte[] execute() {
        try {
            return Files.readAllBytes(Paths.get(connection.getDirPath(), this.queryAsString.split("/")[1]));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
