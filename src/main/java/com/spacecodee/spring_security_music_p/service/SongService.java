package com.spacecodee.spring_security_music_p.service;

import com.spacecodee.spring_security_music_p.data.dto.SongDTO;
import com.spacecodee.spring_security_music_p.data.vo.SongVO;

import java.util.List;

public interface SongService {

    List<SongDTO> listAllSongs();

    List<SongDTO> listAllEnabledSongs();

    List<SongDTO> listAllDisabledSongs();

    SongDTO getSongByName(String songName, String lang);

    SongDTO getSongById(int songId, String lang);

    void createSong(SongVO songVo, String lang);

    void updateSong(SongVO songVo, String lang);

    void disableSong(int songId, String lang);

    void enableSong(int songId, String lang);

    void deleteSong(int songId, String lang);




}
