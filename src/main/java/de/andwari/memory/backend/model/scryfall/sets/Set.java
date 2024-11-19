package de.andwari.memory.backend.model.scryfall.sets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Set {

    private String object;
    private String id;
    private String code;

    @JsonProperty("mtgo_code")
    private String mtgoCode;

    @JsonProperty("arena_code")
    private String arenaCode;

    private String name;
    private String uri;

    @JsonProperty("scryfall_uri")
    private String scryfallUri;

    @JsonProperty("search_uri")
    private String searchUri;

    @JsonProperty("released_at")
    private String releasedAt;

    @JsonProperty("set_type")
    private String setType;

    @JsonProperty("card_count")
    private int cardCount;

    private boolean digital;

    @JsonProperty("nonfoil_only")
    private boolean nonfoilOnly;

    @JsonProperty("foil_only")
    private boolean foilOnly;

    @JsonProperty("icon_svg_uri")
    private String iconSvgUri;

    @JsonProperty("parent_set_code")
    private String parentSetCode;

    @JsonProperty("tcgplayer_id")
    private int tcgplayerId;

    @JsonProperty("block_code")
    private String blockCode;

    private String block;
}
