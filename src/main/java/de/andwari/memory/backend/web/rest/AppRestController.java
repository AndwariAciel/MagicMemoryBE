package de.andwari.memory.backend.web.rest;

import de.andwari.memory.backend.model.rest.CardModel;
import de.andwari.memory.backend.model.rest.SetCardModel;
import de.andwari.memory.backend.model.rest.SetModel;
import de.andwari.memory.backend.service.CardService;
import de.andwari.memory.backend.service.CardsUpdateService;
import de.andwari.memory.backend.service.SetService;
import de.andwari.memory.backend.service.SetUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AppRestController {

    private final SetUpdateService setUpdateService;
    private final CardsUpdateService cardsUpdateService;
    private final SetService setService;
    private final CardService cardService;


    @GetMapping("/test")
    public void test() {
        setUpdateService.updateSets();
    }

    @PostMapping("update-cards/{set-code}")
    public void updateCardsForSet(@PathVariable("set-code") String setCode) {
        cardsUpdateService.updateCardsForSet(setCode);
    }

    @GetMapping(value = "sets", produces = {"application/json"})
    public List<SetModel> getSetList() {
        return setService.getSets();
    }

    @GetMapping(value = "cards/{scryfallId}", produces = {"application/json"})
    public CardModel getCard(@PathVariable String scryfallId) {
        return cardService.getCard(scryfallId);
    }

    @GetMapping(value = "cards", produces = {"application/json"})
    public List<SetCardModel> getSetCards(@RequestParam("code") String code) {
        return setService.getSetCards(code);
    }

}
