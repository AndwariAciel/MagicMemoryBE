package de.andwari.memory.backend.service;

import static de.andwari.memory.backend.service.QueryService.getSetSearch;
import static java.lang.Boolean.FALSE;

import de.andwari.memory.backend.db.entity.CardEntity;
import de.andwari.memory.backend.db.repository.CardRepository;
import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.mapper.CardMapper;
import de.andwari.memory.backend.web.client.ScryfallClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardsUpdateService {

    private final ScryfallClient scryfallClient;
    private final SetRepository setRepository;
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;
    private final MaskMatcher maskMatcher;

    public void updateCardsForSet(String setCode) {
        var set = setRepository.findByCode(setCode)
                .orElseThrow(() -> new IllegalArgumentException("Could not find set with code " + setCode));

        boolean hasMore;
        int page = 1;

        do {
            var cards = scryfallClient.getCards(getSetSearch(setCode), page);
            hasMore = cards.isHasMore();
            cards.getData().stream()
                    .map(card -> cardMapper.toEntity(card, set))
                    .peek(maskMatcher::findFittingDefaultMask)
                    .forEach(this::updateCard);

            page++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (hasMore);
    }

    private void updateCard(CardEntity card) {
        cardRepository.findByScryfallId(card.getScryfallId())
                .ifPresentOrElse(dbCard -> {
                    if (dbCard.hashCode() != card.hashCode()) {
                        card.setId(dbCard.getId());
                        card.setShapes(dbCard.getShapes());
                        card.setReady(FALSE);
                        cardRepository.save(card);
                    }
                }, () -> {
                    card.setReady(FALSE);
                    cardRepository.save(card);
                });
    }

}
