package de.andwari.memory.backend.model.scryfall.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Prices {

    public String usd;

    @JsonProperty("usd_foil")
    public String usdFoil;

    @JsonProperty("usd_etched")
    public Object usdEtched;

    public String eur;

    @JsonProperty("eur_foil")
    public String eurFoil;

    public String tix;

}
