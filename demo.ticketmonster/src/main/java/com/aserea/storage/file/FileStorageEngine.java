gitpackage com.aserea.storage.file;

import com.aserea.storage.StorageEngine;
import com.aserea.storage.StorageEngineConnection;

import java.util.Properties;

public class FileStorageEngine implements StorageEngine {

    private String dirPath;

    public FileStorageEngine(String dirPath) {
        this.dirPath = dirPath;
    }

    @Override
    public FileStorageConnection getConnection() {
        return new FileStorageConnection(dirPath);
    }
}
