package de.andwari.memory.backend.service;

import static de.andwari.memory.backend.model.enums.SetType.EXPANSION;

import de.andwari.memory.backend.db.entity.CardEntity;
import de.andwari.memory.backend.db.repository.CardRepository;
import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.mapper.SetMapper;
import de.andwari.memory.backend.model.rest.SetModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        var readyCards = cardRepository.findBySetCode(set.code()).stream()
                .filter(CardEntity::getReady)
                .count();
        return setMapper.addReadyCards(set, readyCards);

    }
}
