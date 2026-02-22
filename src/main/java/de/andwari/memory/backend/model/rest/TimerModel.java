package de.andwari.memory.backend.model.rest;

public record TimerModel(
        String name,
        String status,
        String cron
) {
}
