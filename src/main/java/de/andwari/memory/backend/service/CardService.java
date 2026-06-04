package de.andwari.memory.backend.service;

import de.andwari.memory.backend.db.repository.CardRepository;
import de.andwari.memory.backend.db.repository.ShapeRepository;
import de.andwari.memory.backend.mapper.CardModelMapper;
import de.andwari.memory.backend.model.rest.CardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ShapeRepository shapeRepository;
    private final CardModelMapper mapper;

    @Transactional(readOnly = true)
    public CardModel getCard(String scryfallId) {
        return cardRepository.findByScryfallId(scryfallId)
                .map(mapper::toModel)
                .orElse(null);
    }

    public void updateCard(CardModel card) {
        cardRepository.findByScryfallId(card.scryfallId())
                .ifPresent(entity -> {
                    var shapeIds = card.shapes().stream()
                            .map(s -> s.id().longValue())
                            .toList();
                    entity.setShapes(shapeRepository.findAllById(shapeIds));
                    entity.setReady(card.ready());
                    cardRepository.save(entity);
                });
    }
}
