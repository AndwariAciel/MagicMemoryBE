package de.andwari.memory.backend;

import de.andwari.memory.backend.service.CardsUpdateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private CardsUpdateService cardsUpdateService;

    @Test
    void contextLoads() {
        cardsUpdateService.updateCardsForSet("fdn");
    }

}
