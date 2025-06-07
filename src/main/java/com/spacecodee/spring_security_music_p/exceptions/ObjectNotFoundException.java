package com.spacecodee.spring_security_music_p.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
