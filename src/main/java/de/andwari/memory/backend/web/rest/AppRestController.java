package de.andwari.memory.backend.web.rest;

import de.andwari.memory.backend.model.rest.SetModel;
import de.andwari.memory.backend.service.CardsUpdateService;
import de.andwari.memory.backend.service.SetService;
import de.andwari.memory.backend.service.SetUpdateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AppRestController {

    private final SetUpdateService setUpdateService;
    private final CardsUpdateService cardsUpdateService;
    private final SetService setService;

    @GetMapping("/test")
    public void test() {
        setUpdateService.updateSets();
    }

    @PostMapping("update-cards/{set-code}")
    public void updateCardsForSet(@PathVariable("set-code") String setCode) {
        cardsUpdateService.updateCardsForSet(setCode);
    }

    @GetMapping(value = "sets", produces = { "application/json" })
    public List<SetModel> getSetList() {
        return setService.getSets();
    }

}
