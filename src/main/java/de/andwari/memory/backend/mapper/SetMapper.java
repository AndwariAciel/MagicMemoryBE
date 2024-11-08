package de.andwari.memory.backend.mapper;

import de.andwari.memory.backend.db.entity.SetEntity;
import de.andwari.memory.backend.model.scryfall.SetModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SetMapper {

    @Mapping(target = "scryfallId", source = "id")
    @Mapping(target = "url", source = "scryfall_uri")
    @Mapping(target = "releaseDate", source = "released_at")
    @Mapping(target = "type", source = "set_type")
    @Mapping(target = "cards", source = "card_count")
    @Mapping(target = "iconUrl", source = "icon_svg_uri")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "released")
    SetEntity toEntity(SetModel set);
}
