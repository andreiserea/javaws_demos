package com.aserea.storage.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteFileQuery extends FileQuery {
    public WriteFileQuery(FileStorageConnection connection, String queryAsString, Object[] params) {
        super(connection, queryAsString, params);
    }

    public byte[] execute() {
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
        return null;
    }
}
