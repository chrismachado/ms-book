package com.ms.book.exception;

import java.util.UUID;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(UUID uuid) {
        super("Could not find the Address ID " + uuid.toString());
    }
}
