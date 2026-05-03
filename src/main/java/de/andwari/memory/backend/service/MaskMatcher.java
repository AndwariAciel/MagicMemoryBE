package de.andwari.memory.backend.service;

import static de.andwari.memory.backend.model.constants.DefaultMasks.DEFAULT_CREATURE_3M;
import static de.andwari.memory.backend.model.constants.DefaultMasks.DEFAULT_SPELL_3M;
import static de.andwari.memory.backend.model.enums.CardLayout.NORMAL;
import static de.andwari.memory.backend.model.enums.CardType.ARTIFACT;
import static de.andwari.memory.backend.model.enums.CardType.ARTIFACT_CREATURE;
import static de.andwari.memory.backend.model.enums.CardType.CREATURE;
import static de.andwari.memory.backend.model.enums.CardType.ENCHANTMENT;
import static de.andwari.memory.backend.model.enums.CardType.INSTANT;
import static de.andwari.memory.backend.model.enums.CardType.LAND;
import static de.andwari.memory.backend.model.enums.CardType.SORCERY;
import static java.util.List.of;
import static java.util.Optional.*;

import de.andwari.memory.backend.db.entity.CardEntity;
import de.andwari.memory.backend.db.entity.MaskEntity;
import de.andwari.memory.backend.db.repository.MaskRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaskMatcher {

    private final MaskRepository maskRepository;

    public void findFittingDefaultMask(CardEntity card) {

        Optional<MaskEntity> result = empty();

        var mana = convertMana(card.getManaCost());

        if (card.getCardLayout().equals(NORMAL)) {
            if (of(CREATURE, ARTIFACT_CREATURE).contains(card.getCardType())) {
                if (mana <= 3) {
                    result = maskRepository.findByName(DEFAULT_CREATURE_3M);
                }
            } else if (of(ARTIFACT, INSTANT, ENCHANTMENT, SORCERY, LAND).contains(card.getCardType())) {
                if (mana <= 3) {
                    result = maskRepository.findByName(DEFAULT_SPELL_3M);
                }
            }
        }

        result.ifPresent(card::setMask);
    }

    private long convertMana(String manaCost) {
        if(Objects.isNull(manaCost))
            return 0L;
        return Pattern.compile("\\{[^}]+}")
                .matcher(manaCost)
                .results()
                .count();
    }

}
