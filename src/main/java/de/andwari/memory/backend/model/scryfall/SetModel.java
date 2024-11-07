package de.andwari.memory.backend.model.scryfall;

import lombok.Data;

@Data
public class SetModel {
    private String object;
    private String id;
    private String code;
    private String mtgo_code;
    private String arena_code;
    private String name;
    private String uri;
    private String scryfall_uri;
    private String search_uri;
    private String released_at;
    private String set_type;
    private int card_count;
    private boolean digital;
    private boolean nonfoil_only;
    private boolean foil_only;
    private String icon_svg_uri;
    private String parent_set_code;
    private int tcgplayer_id;
    private String block_code;
    private String block;
}
