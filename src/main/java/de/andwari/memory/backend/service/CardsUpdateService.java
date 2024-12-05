package de.andwari.memory.backend.service;

import static de.andwari.memory.backend.service.QueryService.getSetSearch;

import de.andwari.memory.backend.db.entity.CardEntity;
import de.andwari.memory.backend.db.repository.CardRepository;
import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.mapper.CardMapper;
import de.andwari.memory.backend.web.client.ScryfallClient;
import java.util.ArrayList;
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

        var cardList = new ArrayList<CardEntity>();

        boolean hasMore;
        int page = 1;

        do {
            var cards = scryfallClient.getCards(getSetSearch(setCode), page);
            hasMore = cards.isHasMore();
            cardList.addAll(
                    cards.getData().stream()
                            .map(card -> cardMapper.toEntity(card, set))
                            .peek(maskMatcher::findFittingDefaultMask)
                            .toList()
            );

            page++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (hasMore);

        cardList.forEach(this::updateCard);
    }

    private void updateCard(CardEntity card) {
        cardRepository.findByScryfallId(card.getScryfallId())
                .ifPresentOrElse(dbCard -> {
                    if (dbCard.hashCode() != card.hashCode()) {
                        card.setId(dbCard.getId());
                        card.setMask(dbCard.getMask());
                        cardRepository.save(card);
                    }
                }, () -> cardRepository.save(card));
    }

}
