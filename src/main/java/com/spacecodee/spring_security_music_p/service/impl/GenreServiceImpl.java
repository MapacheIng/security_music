package com.spacecodee.spring_security_music_p.service.impl;

import com.spacecodee.spring_security_music_p.data.dto.GenreDTO;
import com.spacecodee.spring_security_music_p.data.vo.GenreVO;
import com.spacecodee.spring_security_music_p.exceptions.ExceptionShortComponent;
import com.spacecodee.spring_security_music_p.mapper.dto.GenreDTOMapper;
import com.spacecodee.spring_security_music_p.mapper.vo.GenreVOMapper;
import com.spacecodee.spring_security_music_p.persisance.entity.GenreEntity;
import com.spacecodee.spring_security_music_p.persisance.repository.GenreRepository;
import com.spacecodee.spring_security_music_p.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreDTOMapper genreDTOMapper;
    private final GenreVOMapper genreVOMapper;
    private final ExceptionShortComponent exceptionShortComponent;

    @Override
    public List<GenreDTO> listAllGenres() {
        List<GenreEntity> list = this.genreRepository.findAll();
        return list.stream()
                .map(this.genreDTOMapper::toDto)
                .toList();
    }

    @Override
    public List<GenreDTO> listAllEnabledGenres() {
        List<GenreEntity> enabledGenres = this.genreRepository.findAllByGenreEnabled();
        return enabledGenres.stream()
                .map(this.genreDTOMapper::toDto)
                .toList();
    }

    @Override
    public List<GenreDTO> listAllDisabledGenres() {
        List<GenreEntity> disabledGenres = this.genreRepository.findAllByGenreDisabled();
        return disabledGenres.stream()
                .map(this.genreDTOMapper::toDto)
                .toList();
    }

    @Override
    public GenreDTO getGenreByName(String genreName, String lang) {
        return this.genreRepository.findByGenreName(genreName)
                .map(this.genreDTOMapper::toDto)
                .orElseThrow(() -> this.exceptionShortComponent.notFoundException("genre.found.by.name.not", lang));
    }

    @Override
    public GenreDTO getGenreById(int genreId, String lang) {
        return this.genreRepository.findById(genreId)
                .map(this.genreDTOMapper::toDto)
                .orElseThrow(() -> this.exceptionShortComponent.notFoundException("genre.found.by.id.not", lang));
    }

    @Override
    public void createGenre(GenreVO genreVo, String lang) {
        boolean existGenre = this.genreRepository.existsByGenreName(genreVo.getName());
        if (!existGenre){
            this.genreRepository.save(this.genreVOMapper.voToEntity(genreVo));
        } else {
            throw this.exceptionShortComponent.cannotSaveUpdateException("genre.found.by.name.not", lang);
        }
    }

    @Override
    public void updateGenre(GenreVO genreVo, String lang) {
        boolean existsGenre = this.genreRepository.existsByGenreNameAndGenreIdNot(genreVo.getName(), genreVo.getId());

        if (!existsGenre) {
            GenreEntity entity = this.genreRepository.findById(genreVo.getId())
                    .orElseThrow(() -> this.exceptionShortComponent.notFoundException("genre.found.by.name.not", lang));
            this.genreRepository.save(this.genreVOMapper.partialUpdate(genreVo, entity));
        } else {
            throw this.exceptionShortComponent.cannotSaveUpdateException("genre.found.by.name.not", lang);
        }
    }

    @Override
    public void disableGenre(int genreId, String lang) {
        this.genreRepository
                .findById(genreId)
                .ifPresentOrElse(genreEntity -> {
                            this.genreRepository.save(this.genreDTOMapper.toDisableStatus(genreEntity));
                        },
                        () -> {
                            throw this.exceptionShortComponent.notFoundException("genre.disabled.error", lang);
                        }
                );
    }

    @Override
    public void enableGenre(int genreId, String lang) {
        this.genreRepository
                .findById(genreId)
                .ifPresentOrElse(genreEntity -> {
                            this.genreRepository.save(this.genreDTOMapper.toEnableStatus(genreEntity));
                        },
                        () -> {
                            throw this.exceptionShortComponent.notFoundException("genre.enabled.error", lang);
                        }
                );
    }

    @Override
    public void deleteGenre(int genreId, String lang) {
        Optional<GenreEntity> existGenre = this.genreRepository.findById(genreId);
        if (existGenre.isEmpty()) {
            throw this.exceptionShortComponent.notFoundException("genre.exists.by.id.not", lang);
        }
        this.genreRepository.deleteById(genreId);
    }
}
