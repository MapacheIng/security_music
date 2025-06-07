package com.spacecodee.spring_security_music_p.exceptions;

import com.spacecodee.spring_security_music_p.language.MessageUtilComponent;


public class ExceptionShortComponent {

    private final MessageUtilComponent messageUtilComponent;

    public ExceptionShortComponent(MessageUtilComponent messageUtilComponent) {
        this.messageUtilComponent = messageUtilComponent;
    }

    public CannotSaveUpdateException cannotSaveUpdateException(String message, String locale) {
        return new CannotSaveUpdateException(this.messageUtilComponent.getMessage(message, locale));
    }

    public ObjectNotFoundException notFoundException(String message, String locale) {
        return new ObjectNotFoundException(this.messageUtilComponent.getMessage(message, locale));
    }

    public InvalidPasswordException invalidPasswordException(String message, String locale) {
        return new InvalidPasswordException(this.messageUtilComponent.getMessage(message, locale));
    }

    public NoContentException noContentException(String message, String locale) {
        return new NoContentException(this.messageUtilComponent.getMessage(message, locale));
    }

    public NoCreatedException noCreatedException(String message, String locale) {
        return new NoCreatedException(this.messageUtilComponent.getMessage(message, locale));
    }

    public NoDisabledException noDisabledException(String message, String locale) {
        return new NoDisabledException(this.messageUtilComponent.getMessage(message, locale));
    }

    public NoUpdatedException noUpdatedException(String message, String locale) {
        return new NoUpdatedException(this.messageUtilComponent.getMessage(message, locale));
    }

}
