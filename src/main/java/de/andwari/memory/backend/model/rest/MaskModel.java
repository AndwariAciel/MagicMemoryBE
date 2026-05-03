package de.andwari.memory.backend.model.rest;

import java.util.List;

public record MaskModel(
        Integer id,
        String name,
        Boolean standard,
        List<ShapeModel> shapes
) {
}
