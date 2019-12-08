package org.wahlzeit.model.exceptions;

import java.io.IOException;

public class ClientIOException extends IOException {

    public ClientIOException(String message) {
        super(message);
    }
}
