package de.andwari.memory.backend.model.scryfall.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RelatedUris {

    public String gatherer;

    @JsonProperty("tcgplayer_infinite_articles")
    public String tcgplayerInfiniteArticles;

    @JsonProperty("tcgplayer_infinite_decks")
    public String tcgplayerInfiniteDecks;

    public String edhrec;

}
