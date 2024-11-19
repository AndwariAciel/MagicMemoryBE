package de.andwari.memory.backend.service;

import de.andwari.memory.backend.mapper.CardTypeMapper;
import de.andwari.memory.backend.model.enums.CardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardTypeService {

    private static final String LEGENDARY = "Legendary";

    private final CardTypeMapper cardTypeMapper;

    public CardType convertCardType(String type) {
        type = type.split(" — ")[0];
        if (type.startsWith(LEGENDARY))
            type = type.substring(LEGENDARY.length()).trim();

        return cardTypeMapper.toType(type);
    }
}
