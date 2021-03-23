package com.urise.webapp.sql;

import com.urise.webapp.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    private Properties properties = new Properties();
    private File storageDir;
    private String url;
    private String name;
    private String password;

    private Config() {
        try (InputStream inputStream = new FileInputStream("config/resumes.properties")) {
            properties.load(inputStream);
            storageDir = new File(properties.getProperty("storage.dir"));
            url = properties.getProperty("db.url");
            name = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Config getConfig() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public SqlStorage sqlStorage() {
        return new SqlStorage(url, name, password);
    }
}
