package com.ms.book.exception;

import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(UUID uuid) {
        super("Could not find the Author with ID " + uuid.toString());
    }
}
