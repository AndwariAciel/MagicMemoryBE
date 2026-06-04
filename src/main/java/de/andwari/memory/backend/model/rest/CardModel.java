package de.andwari.memory.backend.model.rest;

import de.andwari.memory.backend.model.enums.CardLayout;
import de.andwari.memory.backend.model.enums.CardType;

import java.util.List;

public record CardModel(
        String name,
        String scryfallId,
        CardType cardType,
        CardLayout cardLayout,
        String set,
        String pictureUri,
        String manaCost,
        List<ShapeModel> shapes,
        Boolean ready
) {
}
