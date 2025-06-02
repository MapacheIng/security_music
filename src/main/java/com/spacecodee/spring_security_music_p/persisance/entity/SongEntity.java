package com.spacecodee.spring_security_music_p.persisance.entity;

import com.spacecodee.spring_security_music_p.enums.GlobalStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name= "song", schema = "public", catalog = "music_project")
public class SongEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "song_id", nullable = false)
    private int songId;

    @Column(name = "song_name", nullable = false, length = 100)
    private String songName;

    @Column(name = "song_artist_name", nullable = false, length = 100)
    private String  songArtistName;

    @Column(name = "song_released_date", nullable = false)
    private LocalDate songReleasedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "song_enabled", nullable = false, length = 100)
    private GlobalStatusEnum songEnabled;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id", nullable = false)
    private GenreEntity genreEntity;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SongEntity that = (SongEntity) object;
        return songId == that.songId && Objects.equals(songName, that.songName)
                && Objects.equals(songArtistName, that.songArtistName)
                && Objects.equals(songReleasedDate, that.songReleasedDate)
                && Objects.equals(songEnabled, that.songEnabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songName, songArtistName, songReleasedDate, songEnabled);
    }
}
