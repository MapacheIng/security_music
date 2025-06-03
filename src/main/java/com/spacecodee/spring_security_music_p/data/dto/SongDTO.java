package com.spacecodee.spring_security_music_p.data.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record SongDTO(
        int id,
        String name,
        String artistName,
        LocalDate releasedDate,
        String enabled,
        GenreDTO genreDTO) implements Serializable {
}
// This code defines a record class `SongDTO` in Java, which is used to represent a song's data transfer object.