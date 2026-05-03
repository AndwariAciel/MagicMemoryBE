package de.andwari.memory.backend.model.rest;


public record SetCardModel(
        String scryfallId,
        String name,
        String pictureUri,
        Boolean ready
) {
}

