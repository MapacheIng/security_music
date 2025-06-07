package com.spacecodee.spring_security_music_p.mapper.vo;

import com.spacecodee.spring_security_music_p.data.vo.GenreVO;
import com.spacecodee.spring_security_music_p.enums.GlobalStatusEnum;
import com.spacecodee.spring_security_music_p.persisance.entity.GenreEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GenreVOMapper {

    @Mapping(target = "genreEnabled", source = "enabled", qualifiedByName = "genreToUpperCase")
    @Mapping(target = "genreName", source = "name")
    @Mapping(target = "genreId", source = "id")
    GenreEntity voToEntity(GenreVO genreEntity);

    @Mapping(target = "genreEnabled", source = "enabled", qualifiedByName = "genreToUpperCase")
    @Mapping(target = "genreName", source = "name")
    @Mapping(target = "genreId", source = "id")
    @Named("mapGenreVoToEntity")
    GenreEntity mapGenreVoToEntity(GenreVO genreVO);

    @Named("genreToUpperCase")
    default GlobalStatusEnum genreToUpperCase(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return GlobalStatusEnum.fromString(s);
    }

    @InheritConfiguration(name = "voToEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GenreEntity partialUpdate(GenreVO genreVO, @MappingTarget GenreEntity genreEntity);

}
