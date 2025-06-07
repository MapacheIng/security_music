package com.spacecodee.spring_security_music_p.service;

import com.spacecodee.spring_security_music_p.data.dto.GenreDTO;
import com.spacecodee.spring_security_music_p.data.vo.GenreVO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> listAllGenres();

    List<GenreDTO> listAllEnabledGenres();

    List<GenreDTO> listAllDisabledGenres();

    GenreDTO getGenreByName(String genreName, String lang);

    GenreDTO getGenreById(int genreId, String lang);

    void createGenre(GenreVO genreVo, String lang);

    void updateGenre(GenreVO genreVo, String lang);

    void disableGenre(int genreId, String lang);

    void enableGenre(int genreId, String lang);

    void deleteGenre(int genreId, String lang);




}
