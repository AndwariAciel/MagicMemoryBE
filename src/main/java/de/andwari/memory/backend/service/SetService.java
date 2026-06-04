package de.andwari.memory.backend.service;

import de.andwari.memory.backend.db.entity.CardEntity;
import de.andwari.memory.backend.db.repository.CardRepository;
import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.mapper.SetMapper;
import de.andwari.memory.backend.model.rest.SetCardModel;
import de.andwari.memory.backend.model.rest.SetModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static de.andwari.memory.backend.model.enums.SetType.EXPANSION;

@Service
@RequiredArgsConstructor
public class SetService {

    private final SetRepository setRepository;
    private final CardRepository cardRepository;
    private final SetMapper setMapper;

    public List<SetModel> getSets() {
        return setRepository.findAll()
                .stream().filter(set -> set.getType() == EXPANSION)
                .map(setMapper::toModel)
                .map(this::getReadyCards)
                .toList();
    }

    private SetModel getReadyCards(SetModel set) {
        var cards = cardRepository.findBySetCode(set.code());
        long totalCards = cards.size();
        long readyCards = cards.stream().filter(CardEntity::getReady).count();
        return setMapper.addReadyCards(set, totalCards, readyCards);
    }

    @Transactional(readOnly = true)
    public List<SetCardModel> getSetCards(String code) {
        return cardRepository.findBySetCode(code)
                .stream()
                .map(setMapper::toCardModel)
                .toList();
    }
}
