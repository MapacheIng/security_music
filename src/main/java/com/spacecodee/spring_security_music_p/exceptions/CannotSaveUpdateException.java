package com.spacecodee.spring_security_music_p.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CannotSaveUpdateException extends RuntimeException {
    public CannotSaveUpdateException(String message) {
        super(message);
    }
    public CannotSaveUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
