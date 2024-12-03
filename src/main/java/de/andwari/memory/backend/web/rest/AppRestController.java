package de.andwari.memory.backend.web.rest;

import de.andwari.memory.backend.service.CardsUpdateService;
import de.andwari.memory.backend.service.SetUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AppRestController {

    private final SetUpdateService setUpdateService;
    private final CardsUpdateService cardsUpdateService;

    @GetMapping("/test")
    public void test() {
        setUpdateService.updateSets();
    }

    @PostMapping("update-cards/{set-code}")
    public void updateCardsForSet(@PathVariable("set-code") String setCode) {
        cardsUpdateService.updateCardsForSet(setCode);
    }

}
