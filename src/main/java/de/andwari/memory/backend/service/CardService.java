package de.andwari.memory.backend.service;

import de.andwari.memory.backend.db.repository.CardRepository;
import de.andwari.memory.backend.mapper.CardModelMapper;
import de.andwari.memory.backend.model.rest.CardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardModelMapper mapper;

    @Transactional(readOnly = true)
    public CardModel getCard(String scryfallId) {
        return cardRepository.findByScryfallId(scryfallId)
                .map(mapper::toModel)
                .orElse(null);
    }

}
