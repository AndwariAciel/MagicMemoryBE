package de.andwari.memory.backend.service;

import de.andwari.memory.backend.db.entity.CardEntity;
import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.mapper.CardMapper;
import de.andwari.memory.backend.web.client.ScryfallClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static de.andwari.memory.backend.service.QueryService.getSetSearch;

@Service
@RequiredArgsConstructor
public class CardsUpdateService {

    private final ScryfallClient scryfallClient;
    private final SetRepository setRepository;
    private final CardMapper cardMapper;

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
                            .toList()
            );

            page++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (hasMore);

        for (CardEntity cardEntity : cardList) {
            System.out.println(cardEntity.getName());
        }
    }
}
