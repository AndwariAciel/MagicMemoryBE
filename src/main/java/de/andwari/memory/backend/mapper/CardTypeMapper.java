package de.andwari.memory.backend.mapper;

import de.andwari.memory.backend.model.enums.CardType;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CardTypeMapper {

    @ValueMapping(target = "ARTIFACT", source = "Artifact")
    @ValueMapping(target = "CREATURE", source = "Creature")
    @ValueMapping(target = "ENCHANTMENT", source = "Enchantment")
    @ValueMapping(target = "LAND", source = "Land")
    @ValueMapping(target = "PLANESWALKER", source = "Planeswalker")
    @ValueMapping(target = "INSTANT", source = "Instant")
    @ValueMapping(target = "SORCERY", source = "Sorcery")
    @ValueMapping(target = "ARTIFACT_CREATURE", source = "Artifact Creature")
    @ValueMapping(target = "BASIC_LAND", source = "Basic Land")
    @ValueMapping(target = "BATTLE", source = "Battle")
    CardType toType(String type);
}
