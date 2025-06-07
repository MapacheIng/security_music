package com.spacecodee.spring_security_music_p.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoUpdatedException extends RuntimeException {

    public NoUpdatedException(String message) {
        super(message);
    }
    public NoUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
