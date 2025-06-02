package com.spacecodee.spring_security_music_p.data.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SongVO implements Serializable {
    private int id;
    @Size(min = 4, max = 100, message = "Name must be between 4 and 100 characters")
    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Size(min = 4, max = 100, message = "artistName must be between 4 and 100 characters")
    @NotEmpty(message = "artistName cannot be empty")
    @NotBlank(message = "artistName cannot be blank")
    private String artistName;
    private LocalDate releaseDate;
    private String enabled;
    @NotNull(message = "Genre cannot be null")
    private GenreVO genreVO;
}
