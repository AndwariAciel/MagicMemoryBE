package de.andwari.memory.backend.model.scryfall.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CardsRoot {

    private String object;

    @JsonProperty("total_cards")
    private int totalCards;

    @JsonProperty("has_more")
    private boolean hasMore;

    @JsonProperty("next_page")
    private String nextPage;

    private ArrayList<CardData> data;

}
