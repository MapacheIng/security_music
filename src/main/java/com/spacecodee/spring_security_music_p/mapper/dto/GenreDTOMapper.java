package com.spacecodee.spring_security_music_p.mapper.dto;

import com.spacecodee.spring_security_music_p.data.dto.GenreDTO;
import com.spacecodee.spring_security_music_p.enums.GlobalStatusEnum;
import com.spacecodee.spring_security_music_p.persisance.entity.GenreEntity;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GenreDTOMapper {
    @Mapping(target = "id", source = "genreId")
    @Mapping(target = "name", source = "genreName")
    @Mapping(target = "enabled", source = "genreEnabled", qualifiedByName = "genreEnumToString")
    GenreDTO toDto(GenreEntity genreEntity);

    @Named("genreEnumToString")
    default String genreEnumToString(GlobalStatusEnum e) {
        if (e == null) {
            return "";
        }
        return e.name();
    }

    //test
    default GenreEntity toEnableStatus(@NotNull GenreEntity genreEntity){
        genreEntity.setGenreEnabled(GlobalStatusEnum.ENABLED);
        return genreEntity;
    }

    default GenreEntity toDisableStatus(@NotNull GenreEntity genreEntity) {
        genreEntity.setGenreEnabled(GlobalStatusEnum.DISABLED);
        return genreEntity;
    }

}
