package com.spacecodee.spring_security_music_p.mapper.dto;

import com.spacecodee.spring_security_music_p.data.dto.SongDTO;
import com.spacecodee.spring_security_music_p.enums.GlobalStatusEnum;
import com.spacecodee.spring_security_music_p.persisance.entity.SongEntity;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {GenreDTOMapper.class})
public interface SongDTOMapper {

    @Mapping(target = "id", source = "songId")
    @Mapping(target = "name", source = "songName")
    @Mapping(target = "artistName", source = "songArtistName")
    @Mapping(target = "releasedDate", source = "songReleasedDate")
    @Mapping(target = "genreDTO", source = "genreEntity")
    @Mapping(target = "enabled", source = "songEnabled", qualifiedByName = "songEnumToString")
    SongDTO toDto(SongEntity songEntity);

    @Named("songEnumToString")
    default String songEnumToString(GlobalStatusEnum e) {
        if (e == null) return "";
        return e.name();
    }

    //test
    default SongEntity toEnableStatus(@NotNull SongEntity songEntity){
        songEntity.setSongEnabled(GlobalStatusEnum.ENABLED);
        return songEntity;
    }

    default SongEntity toDisableStatus(@NotNull SongEntity songEntity) {
        songEntity.setSongEnabled(GlobalStatusEnum.DISABLED);
        return songEntity;
    }

}
