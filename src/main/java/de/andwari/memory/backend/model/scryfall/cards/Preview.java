package de.andwari.memory.backend.model.scryfall.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Preview {

    public String source;

    @JsonProperty("source_uri")
    public String sourceUri;

    @JsonProperty("previewed_at")
    public String previewedAt;
}
