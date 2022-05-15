package com.ms.book.exception;

import java.util.UUID;

public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException(UUID uuid) {
        super("Could not find publisher id " + uuid.toString());
    }
}
