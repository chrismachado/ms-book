package com.ms.book.exception;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(UUID uuid) {
        super("Could not find book id " + uuid.toString());
    }
}
