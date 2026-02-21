package de.andwari.memory.backend.web.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@AutoConfigureWireMock(port = 0, stubs = "classpath:/mapping/**/*.json")
@TestPropertySource(properties = "scryfall.url=http://localhost:${wiremock.server.port}")
class ScryfallClientTest {

    @Autowired
    private ScryfallClient scryFallClient;

    @Test
    void testClient() {
        var allSets = scryFallClient.getAllSets();
        Assertions.assertEquals(971, allSets.getData().size());
    }

}
