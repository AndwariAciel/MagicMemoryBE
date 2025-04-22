package de.andwari.memory.backend.web.rest;

import de.andwari.memory.backend.db.entity.SetEntity;
import de.andwari.memory.backend.service.CardsUpdateService;
import de.andwari.memory.backend.service.SetService;
import de.andwari.memory.backend.service.SetUpdateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
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

    @GetMapping("sets")
    public List<SetEntity> getSetList() {
        return setService.getSets();
    }

}
