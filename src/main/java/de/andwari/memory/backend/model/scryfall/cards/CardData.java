package de.andwari.memory.backend.model.scryfall.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CardData {

    public String object;
    public String id;
    public String oracleId;

    @JsonProperty("multiverse_ids")
    public ArrayList<Integer> multiverseIds;

    @JsonProperty("mtgo_id")
    public int mtgoId;

    @JsonProperty("tcgplayer_id")
    public int tcgplayerId;

    @JsonProperty("cardmarket_id")
    public int cardmarketId;

    public String name;
    public String lang;

    @JsonProperty("released_at")
    public String releasedAt;

    public String uri;
    public String scryfallUri;
    public String layout;

    @JsonProperty("highres_image")
    public boolean highresImage;

    @JsonProperty("image_status")
    public String imageStatus;

    @JsonProperty("image_uris")
    public ImageUris imageUris;

    @JsonProperty("mana_cost")
    public String manaCost;

    public int cmc;

    @JsonProperty("type_line")
    public String typeLine;

    @JsonProperty("oracle_text")
    public String oracleText;

    public String power;
    public String toughness;

    public ArrayList<String> colors;

    @JsonProperty("color_indicator")
    public ArrayList<String> colorIndicator;

    @JsonProperty("color_identity")
    public ArrayList<String> colorIdentity;

    public ArrayList<String> keywords;

    @JsonProperty("all_parts")
    public ArrayList<AllPart> allParts;

    public Legalities legalities;
    public ArrayList<String> games;

    public boolean reserved;
    public boolean foil;
    public boolean nonfoil;

    @JsonProperty("finishes")
    public ArrayList<String> finishes;

    @JsonProperty("oversized")
    public boolean oversized;

    public boolean promo;
    public boolean reprint;
    public boolean variation;

    @JsonProperty("set_id")
    public String setId;

    public String set;

    @JsonProperty("set_name")
    public String setName;

    @JsonProperty("set_type")
    public String setType;

    public String setUri;

    @JsonProperty("set_search_uri")
    public String setSearchUri;

    @JsonProperty("scryfall_set_uri")
    public String scryfallSetUri;

    @JsonProperty("rulings_uri")
    public String rulingsUri;

    @JsonProperty("prints_search_uri")
    public String printsSearchUri;

    public String collectorNumber;

    public boolean digital;
    public String rarity;

    @JsonProperty("card_back_id")
    public String cardBackId;

    public String artist;

    @JsonProperty("artist_ids")
    public ArrayList<String> artistIds;

    @JsonProperty("illustration_id")
    public String illustrationId;

    @JsonProperty("border_color")
    public String borderColor;

    public String frame;

    @JsonProperty("frame_effects")
    public ArrayList<String> frameEffects;

    public String securityStamp;

    @JsonProperty("full_art")
    public boolean fullArt;

    public boolean textless;
    public boolean booster;

    @JsonProperty("story_spotlight")
    public boolean storySpotlight;

    public int edhrecRank;
    public int pennyRank;

    public Preview preview;
    public Prices prices;
    public RelatedUris relatedUris;
    public PurchaseUris purchaseUris;

    @JsonProperty("arena_id")
    public int arenaId;

    @JsonProperty("card_faces")
    public ArrayList<CardFace> cardFaces;

    public String flavorText;

    @JsonProperty("mtgo_foil_id")
    public int mtgoFoilId;

    @JsonProperty("promo_types")
    public ArrayList<String> promoTypes;

    @JsonProperty("produced_mana")
    public ArrayList<String> producedMana;

    public String watermark;

}
