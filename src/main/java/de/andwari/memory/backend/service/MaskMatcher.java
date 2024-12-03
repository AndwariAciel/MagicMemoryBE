package de.andwari.memory.backend.service;

import static de.andwari.memory.backend.model.constants.DefaultMasks.DEFAULT_CREATURE_3M;
import static de.andwari.memory.backend.model.constants.DefaultMasks.DEFAULT_SPELL_3M;
import static de.andwari.memory.backend.model.enums.CardType.ARTIFACT;
import static de.andwari.memory.backend.model.enums.CardType.ARTIFACT_CREATURE;
import static de.andwari.memory.backend.model.enums.CardType.CREATURE;
import static de.andwari.memory.backend.model.enums.CardType.ENCHANTMENT;
import static de.andwari.memory.backend.model.enums.CardType.INSTANT;
import static de.andwari.memory.backend.model.enums.CardType.LAND;
import static de.andwari.memory.backend.model.enums.CardType.SORCERY;
import static java.util.List.of;

import de.andwari.memory.backend.db.entity.MaskEntity;
import de.andwari.memory.backend.db.repository.MaskRepository;
import de.andwari.memory.backend.model.scryfall.cards.CardData;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaskMatcher {

    private final CardTypeService cardTypeService;
    private final MaskRepository maskRepository;

    public Optional<MaskEntity> findFittingDefaultMask(CardData card) {

        Optional<MaskEntity> result = Optional.empty();

        var mana = convertMana(card.getManaCost());
        var type = cardTypeService.convertCardType(card.getTypeLine());
        var layout = card.getLayout();

        if (layout.equals("normal")) {
            if (of(CREATURE, ARTIFACT_CREATURE).contains(type)) {
                if (mana <= 3) {
                    result = maskRepository.findByName(DEFAULT_CREATURE_3M);
                }
            } else if (of(ARTIFACT, INSTANT, ENCHANTMENT, SORCERY, LAND).contains(type)) {
                if (mana <= 3) {
                    result = maskRepository.findByName(DEFAULT_SPELL_3M);
                }
            }
        }
        return result;
    }

    private long convertMana(String manaCost) {
        return Pattern.compile("\\{[^}]+}")
                .matcher(manaCost)
                .results()
                .count();
    }

}
