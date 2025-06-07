package com.spacecodee.spring_security_music_p.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoCreatedException extends RuntimeException {
    public NoCreatedException(String message) {
        super(message);
    }
    public NoCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
