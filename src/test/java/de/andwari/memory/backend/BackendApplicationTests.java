package de.andwari.memory.backend;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import de.andwari.memory.backend.service.CardsUpdateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@AutoConfigureWireMock(port = 0, stubs = "classpath:/mapping/**/*.json")
@SpringBootTest(webEnvironment = RANDOM_PORT)
class BackendApplicationTests {

    @Autowired
    private CardsUpdateService cardsUpdateService;

    @Test
    void contextLoads() {
        cardsUpdateService.updateCardsForSet("fdn");
    }

}
