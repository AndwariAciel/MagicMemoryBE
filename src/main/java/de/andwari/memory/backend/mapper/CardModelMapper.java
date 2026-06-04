package de.andwari.memory.backend.mapper;

import de.andwari.memory.backend.db.entity.CardEntity;
import de.andwari.memory.backend.db.entity.ShapeEntity;
import de.andwari.memory.backend.model.rest.CardModel;
import de.andwari.memory.backend.model.rest.ShapeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CardModelMapper {

    ShapeModel toModel(ShapeEntity shape);

    @Mapping(target = "set", source = "set.code")
    CardModel toModel(CardEntity card);

    ShapeEntity toEntity(ShapeModel model);
}
