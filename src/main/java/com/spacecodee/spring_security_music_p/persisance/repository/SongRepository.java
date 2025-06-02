package com.spacecodee.spring_security_music_p.persisance.repository;


import com.spacecodee.spring_security_music_p.persisance.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<SongEntity, Integer> {

    @Query("SELECT s FROM SongEntity s WHERE s.songEnabled = 'ENABLED'")
    List<SongEntity> findAllBySongEnabled();

    @Query("SELECT s FROM SongEntity s WHERE s.songEnabled = 'DISABLED'")
    List<SongEntity> findAllBySongDisabled();

    Optional<SongEntity> findBySongName(String songName);

    boolean existsBySongNameIgnoreCaseAndSongArtistNameIgnoreCase(String songName, String songArtist);

    boolean existsBySongNameIgnoreCaseAndSongArtistNameIgnoreCaseAndSongIdNot(String songName, String songArtist, Integer id);

}
