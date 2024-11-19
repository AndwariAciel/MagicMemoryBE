package de.andwari.memory.backend.model.scryfall.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CardFace {

    public String object;
    public String name;

    @JsonProperty("mana_cost")
    public String manaCost;

    @JsonProperty("type_line")
    public String typeLine;

    @JsonProperty("oracle_text")
    public String oracleText;

    public ArrayList<String> colors;
    public String power;
    public String toughness;

    @JsonProperty("flavor_text")
    public String flavorText;

    public String artist;

    @JsonProperty("artist_id")
    public String artistId;

    @JsonProperty("illustration_id")
    public String illustrationId;

    @JsonProperty("image_uris")
    public ImageUris imageUris;

    @JsonProperty("color_indicator")
    public ArrayList<String> colorIndicator;

    public String defense;

}
