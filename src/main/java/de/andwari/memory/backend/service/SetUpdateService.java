package de.andwari.memory.backend.service;

import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.mapper.SetMapper;
import de.andwari.memory.backend.web.client.ScryfallClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetUpdateService {

    private final ScryfallClient scryfallClient;
    private final SetRepository setRepository;
    private final SetMapper setMapper;


    public void updateSets() {

        scryfallClient.getAllSets().getData()
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
    }
}
