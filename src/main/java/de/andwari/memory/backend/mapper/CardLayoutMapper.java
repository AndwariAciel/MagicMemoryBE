package de.andwari.memory.backend.mapper;

import de.andwari.memory.backend.model.enums.CardLayout;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CardLayoutMapper {

    @ValueMapping(target = "NORMAL", source = "normal")
    CardLayout toLayout(String layout);
}
