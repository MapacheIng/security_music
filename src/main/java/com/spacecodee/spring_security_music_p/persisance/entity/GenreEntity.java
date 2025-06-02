package com.spacecodee.spring_security_music_p.persisance.entity;

import com.spacecodee.spring_security_music_p.enums.GlobalStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "genre", schema = "public", catalog = "music_project")
public class GenreEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "genre_id", nullable = false)
    private int genreId;


    @Column(name = "genre_name", nullable = false, length = 50)
    private String genreName;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre_enable", nullable = false, length = 50 )
    private GlobalStatusEnum genreEnable;



    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GenreEntity that = (GenreEntity) object;
        return genreId == that.genreId && Objects.equals(genreName, that.genreName) && Objects.equals(genreEnable, that.genreEnable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, genreName, genreEnable);
    }
}
