package de.andwari.memory.backend.model.scryfall.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AllPart {

    public String object;
    public String id;
    public String component;
    public String name;
    @JsonProperty("type_line")
    public String typeLine;
    public String uri;

}
