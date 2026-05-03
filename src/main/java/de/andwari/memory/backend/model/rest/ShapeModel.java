package de.andwari.memory.backend.model.rest;

import de.andwari.memory.backend.model.enums.ShapeType;

public record ShapeModel(
        Integer id,
        ShapeType type,
        Integer x,
        Integer y,
        Integer width,
        Integer height
) {
}
