package com.spacecodee.spring_security_music_p.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoDisabledException extends RuntimeException {

    public NoDisabledException(String message) {
        super(message);
    }

    public NoDisabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
