package com.spacecodee.spring_security_music_p.mapper.vo;

import com.spacecodee.spring_security_music_p.data.vo.SongVO;
import com.spacecodee.spring_security_music_p.enums.GlobalStatusEnum;
import com.spacecodee.spring_security_music_p.persisance.entity.SongEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {GenreVOMapper.class})
public interface SongVOMapper {

    @Mapping(target = "songId", source = "id")
    @Mapping(target = "songName", source = "name")
    @Mapping(target = "songArtistName", source = "artistName")
    @Mapping(target = "songReleasedDate", source = "releasedDate")
    @Mapping(target = "songEnabled", source = "enabled", qualifiedByName = "songToUpperCase")
    @Mapping(target = "genreEntity", source = "genreVO", qualifiedByName = "mapGenreVoToEntity")
    SongEntity voToEntity(SongVO songVO);


    @Named("songToUpperCase")
    default GlobalStatusEnum songToUpperCase(String s) {
        if (s == null) {
            return null;
        }
        return GlobalStatusEnum.fromString(s);
    }

    @InheritConfiguration(name = "voToEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SongEntity partialUpdate(SongVO songVO, @MappingTarget SongEntity songEntity);

}
