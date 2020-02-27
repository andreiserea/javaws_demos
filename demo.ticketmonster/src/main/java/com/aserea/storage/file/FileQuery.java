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

    private FileQuery(FileStorageConnection connection, String queryAsString, Object[] params) {
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

    private static class ReadFileQuery extends FileQuery {
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

    private static class WriteFileQuery extends FileQuery {
        public WriteFileQuery(FileStorageConnection connection, String queryAsString, Object[] params) {
            super(connection, queryAsString, params);
        }

        public byte[] execute() {
            Path path = Paths.get(this.connection.getDirPath(), this.queryAsString.split("/")[1]);
            File f = path.toFile();
            try(BufferedWriter fw = new BufferedWriter(new FileWriter(f, true))) {
                for(int i = 0; i < params.length; i++) {
                    if (f.length() != 0 || i > 0) {
                        fw.newLine();
                    }
                    fw.write(params[i].toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
