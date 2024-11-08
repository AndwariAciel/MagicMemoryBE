package de.andwari.memory.backend.service;

import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.web.client.ScryfallClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetUpdateService {

    private final ScryfallClient scryfallClient;
    private final SetRepository setRepository;

    public void updateSets() {

    }

}
