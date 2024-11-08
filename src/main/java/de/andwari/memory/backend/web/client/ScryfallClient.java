package de.andwari.memory.backend.web.client;

import de.andwari.memory.backend.model.scryfall.SetsRootModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "scryfallClient", url = "https://api.scryfall.com")
public interface ScryfallClient {

    @GetMapping("/sets")
    SetsRootModel getAllSets();

}