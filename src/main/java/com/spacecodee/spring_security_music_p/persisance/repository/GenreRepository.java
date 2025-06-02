package com.spacecodee.spring_security_music_p.persisance.repository;

import com.spacecodee.spring_security_music_p.persisance.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

    @Query("SELECT g FROM GenreEntity g WHERE g.genreEnabled = 'ENABLED'")
    List<GenreEntity> findAllByGenreEnabled();

    @Query("SELECT g FROM GenreEntity g WHERE g.genreEnabled = 'DISABLED'")
    List<GenreEntity> findAllByGenreDisabled();

    Optional<GenreEntity> findByGenreName(String genreName);

    boolean existsByGenreName(String genreName);

    boolean existsByGenreNameAndIdNot(String genreName, Integer id);

}
