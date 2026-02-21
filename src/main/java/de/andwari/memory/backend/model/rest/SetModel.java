package de.andwari.memory.backend.model.rest;

public record SetModel(
    String name,
    String code,
    String scryfallId,
    String type,
    String url,
    String releaseDate,
    int cards,
    int cardsReady,
    String iconUrl,
    boolean released
) {
}
