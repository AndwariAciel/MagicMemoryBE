package de.andwari.memory.backend.model.enums;

import lombok.Getter;

@Getter
public enum SetType {

    CORE("core"),
    EXPANSION("expansion"),
    MASTERS("masters"),
    ALCHEMY("alchemy"),
    MASTERPIECE("masterpiece"),
    ARSENAL("arsenal"),
    FROM_THE_VAULT("from_the_vault"),
    SPELLBOOK("spellbook"),
    PREMIUM_DECK("premium_deck"),
    DUEL_DECK("duel_deck"),
    DRAFT_INNOVATION("draft_innovation"),
    TREASURE_CHEST("treasure_chest"),
    COMMANDER("commander"),
    PLANECHASE("planechase"),
    ARCHENEMY("archenemy"),
    VANGUARD("vanguard"),
    FUNNY("funny"),
    STARTER("starter"),
    BOX("box"),
    PROMO("promo"),
    TOKEN("token"),
    MEMORABILIA("memorabilia"),
    MINIGAME("minigame");

    private final String type;

    SetType(String type) {
        this.type = type;
    }

}