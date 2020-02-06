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

public class FileQuery implements Query {

    private FileStorageConnection connection;
    private String queryAsString;
    private Object[] params;

    public FileQuery(FileStorageConnection connection, String queryAsString, Object[] params) {
        this.connection = connection;
        this.queryAsString = queryAsString;
        this.params = params;
    }

    @Override
    public byte[] execute() {
        if (this.queryAsString.startsWith("r")) {
            return this.executeRead();
        } else if (this.queryAsString.startsWith("w")) {
            this.executeWrite();
            return null;
        } else {
            // should be checked at construction time - violates object contract
            throw new IllegalArgumentException("Not a valid query: " + this.queryAsString);
        }

    }

    private byte[] executeRead() {
        try {
            return Files.readAllBytes(Paths.get(connection.getDirPath(), this.queryAsString.split("/")[1]));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void executeWrite() {
        Path path = Paths.get(connection.getDirPath(), this.queryAsString.split("/")[1]);
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
    }
}
