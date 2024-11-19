package de.andwari.memory.backend.mapper;

import de.andwari.memory.backend.db.entity.SetEntity;
import de.andwari.memory.backend.model.enums.SetType;
import de.andwari.memory.backend.model.scryfall.sets.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public abstract class SetMapper {

    @Mapping(target = "scryfallId", source = "id")
    @Mapping(target = "url", source = "scryfallUri")
    @Mapping(target = "releaseDate", source = "releasedAt")
    @Mapping(target = "type", source = "setType")
    @Mapping(target = "cards", source = "cardCount")
    @Mapping(target = "iconUrl", source = "iconSvgUri")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "released")
    public abstract SetEntity toEntity(Set set);

    protected SetType getSetType(String type) {
        return Stream.of(SetType.values())
                .filter(t -> t.getType().equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown enum type " + type));
    }
}
