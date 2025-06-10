package com.spacecodee.spring_security_music_p.service.impl;

import com.spacecodee.spring_security_music_p.data.dto.SongDTO;
import com.spacecodee.spring_security_music_p.data.vo.SongVO;
import com.spacecodee.spring_security_music_p.exceptions.ExceptionShortComponent;
import com.spacecodee.spring_security_music_p.mapper.dto.SongDTOMapper;
import com.spacecodee.spring_security_music_p.mapper.vo.SongVOMapper;
import com.spacecodee.spring_security_music_p.persisance.entity.GenreEntity;
import com.spacecodee.spring_security_music_p.persisance.entity.SongEntity;
import com.spacecodee.spring_security_music_p.persisance.repository.SongRepository;
import com.spacecodee.spring_security_music_p.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongDTOMapper songDTOMapper;
    private final SongVOMapper songVOMapper;
    private final ExceptionShortComponent exceptionShortComponent;

    @Override
    public List<SongDTO> listAllSongs() {
        List<SongEntity> list = this.songRepository.findAll();
        return list.stream()
                .map(this.songDTOMapper::toDto)
                .toList();
    }

    @Override
    public List<SongDTO> listAllEnabledSongs() {
        List<SongEntity> enabledGenres = this.songRepository.findAllBySongEnabled();
        return enabledGenres.stream()
                .map(this.songDTOMapper::toDto)
                .toList();
    }

    @Override
    public List<SongDTO> listAllDisabledSongs() {
        List<SongEntity> enabledGenres = this.songRepository.findAllBySongDisabled();
        return enabledGenres.stream()
                .map(this.songDTOMapper::toDto)
                .toList();
    }

    @Override
    public SongDTO getSongByName(String songName, String lang) {
        return this.songRepository.findBySongName(songName)
                .map(this.songDTOMapper::toDto)
                .orElseThrow(() -> this.exceptionShortComponent.notFoundException("song.found.by.name.not", lang));
    }

    @Override
    public SongDTO getSongById(int songId, String lang) {
        return this.songRepository.findById(songId)
                .map(this.songDTOMapper::toDto)
                .orElseThrow(() -> this.exceptionShortComponent.notFoundException("song.found.by.id.not", lang));
    }

    @Override
    public void createSong(SongVO songVo, String lang) {
        boolean existSong = this.songRepository.existsBySongNameIgnoreCaseAndSongArtistNameIgnoreCase(songVo.getName(), songVo.getArtistName());
        if (!existSong){
            this.songRepository.save(this.songVOMapper.voToEntity(songVo));
        } else {
            throw this.exceptionShortComponent.cannotSaveUpdateException("song.found.by.name.not", lang);
        }
    }

    @Override
    public void updateSong(SongVO songVo, String lang) {
        boolean existsSong = this.songRepository.existsBySongNameIgnoreCaseAndSongArtistNameIgnoreCaseAndSongIdNot(songVo.getName(), songVo.getArtistName(), songVo.getId());

        if (!existsSong) {
            SongEntity entity = this.songRepository.findById(songVo.getId())
                    .orElseThrow(() -> this.exceptionShortComponent.notFoundException("song.found.by.name.not", lang));
            this.songRepository.save(this.songVOMapper.partialUpdate(songVo, entity));
        } else {
            throw this.exceptionShortComponent.cannotSaveUpdateException("song.found.by.name.not", lang);
        }
    }

    @Override
    public void disableSong(int songId, String lang) {
        this.songRepository
                .findById(songId)
                .ifPresentOrElse(songEntity -> {
                            this.songRepository.save(this.songDTOMapper.toDisableStatus(songEntity));
                        },
                        () -> {
                            throw this.exceptionShortComponent.notFoundException("song.disabled.error", lang);
                        }
                );
    }

    @Override
    public void enableSong(int songId, String lang) {
        this.songRepository
                .findById(songId)
                .ifPresentOrElse(songEntity -> {
                            this.songRepository.save(this.songDTOMapper.toEnableStatus(songEntity));
                        },
                        () -> {
                            throw this.exceptionShortComponent.notFoundException("song.disabled.error", lang);
                        }
                );
    }

    @Override
    public void deleteSong(int songId, String lang) {
        Optional<SongEntity> existGenre = this.songRepository.findById(songId);
        if (existGenre.isEmpty()) {
            throw this.exceptionShortComponent.notFoundException("song.exists.by.id.not", lang);
        }
        this.songRepository.deleteById(songId);
    }
}
