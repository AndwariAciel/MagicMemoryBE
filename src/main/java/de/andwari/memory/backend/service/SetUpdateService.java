package de.andwari.memory.backend.service;

import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.mapper.SetMapper;
import de.andwari.memory.backend.web.client.ScryfallClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SetUpdateService {

    private final ScryfallClient scryfallClient;
    private final SetRepository setRepository;
    private final SetMapper setMapper;


    public void updateSets() {

        log.info("Started updateSets");
        scryfallClient.getAllSets()
                .getData()
                .stream()
                .map(setMapper::toEntity)
                .forEach(
                        set ->
                                setRepository.findByScryfallId(set.getScryfallId())
                                        .ifPresentOrElse(s -> {
                                            if (s.hashCode() != set.hashCode()) {
                                                set.setId(s.getId());
                                                setRepository.save(set);
                                            }
                                        }, () -> setRepository.save(set))
                );
        log.info("Finished updateSets");
    }
}
