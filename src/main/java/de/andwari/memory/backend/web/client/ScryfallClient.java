package de.andwari.memory.backend.web.client;

import de.andwari.memory.backend.model.scryfall.cards.CardsRoot;
import de.andwari.memory.backend.model.scryfall.sets.SetsRoot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "scryfallClient", url = "https://api.scryfall.com")
public interface ScryfallClient {

    @GetMapping("/sets")
    SetsRoot getAllSets();

    @GetMapping("cards/search")
    CardsRoot getCards(@RequestParam("q") String query, @RequestParam("page") int page);

}
