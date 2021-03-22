package com.urise.webapp.exception;

import java.sql.SQLException;

public class StorageException extends RuntimeException {

    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message) {
        super(message);
        this.uuid = "";
    }

    public String getUuid() {
        return uuid;
    }
}
