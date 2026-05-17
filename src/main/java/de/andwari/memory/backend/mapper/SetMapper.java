package de.andwari.memory.backend.mapper;

import de.andwari.memory.backend.db.entity.CardEntity;
import de.andwari.memory.backend.db.entity.SetEntity;
import de.andwari.memory.backend.model.enums.SetType;
import de.andwari.memory.backend.model.rest.SetCardModel;
import de.andwari.memory.backend.model.rest.SetModel;
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

    @Mapping(target = "cardsReady", source = "cardsReady")
    public abstract SetModel addReadyCards(SetModel set, long cardsReady);

    public abstract SetModel toModel(SetEntity set);

    @Mapping(target = "hasMask", expression = "java(card.getMask() != null)")
    public abstract SetCardModel toCardModel(CardEntity card);

    protected SetType getSetType(String type) {
        return Stream.of(SetType.values())
                .filter(t -> t.getType().equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown enum type " + type));
    }
}
