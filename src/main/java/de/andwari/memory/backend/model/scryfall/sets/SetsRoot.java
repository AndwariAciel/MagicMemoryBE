package de.andwari.memory.backend.model.scryfall.sets;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SetsRoot {

    private String object;

    @JsonProperty("has_more")
    private boolean hasMore;
    private ArrayList<Set> data;
}
