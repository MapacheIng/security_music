package com.spacecodee.spring_security_music_p.language;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Getter
public class MessageUtilComponent {

    private final MessageSource messageSource;

    public MessageUtilComponent(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String message, Object[] args, String locale) {
        return this.getMessageSource().getMessage(message, args, this.getLocaleApp(locale));
    }

    public String getMessage(String message, String locale) {
        return this.getMessageSource().getMessage(message, null, this.getLocaleApp(locale));
    }

    private Locale getLocaleApp(@NotNull String locale) {
        return locale.equalsIgnoreCase("en") ? Locale.ENGLISH : Locale.forLanguageTag("es");
    }
}
