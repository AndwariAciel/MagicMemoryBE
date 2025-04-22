package de.andwari.memory.backend.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SetModel {

    private String id;
    private String name;
    private String code;
    private String scryfallId;
    private String setType;
    private String blockCode;
    private String blockName;
    private String parentSetCode;
    private String cardCount;
    private boolean digital;

}
