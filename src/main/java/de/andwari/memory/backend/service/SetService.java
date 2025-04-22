package de.andwari.memory.backend.service;

import de.andwari.memory.backend.db.entity.SetEntity;
import de.andwari.memory.backend.db.repository.SetRepository;
import de.andwari.memory.backend.model.enums.SetType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetService {

    private final SetRepository setRepository;

    public List<SetEntity> getSets() {
        return setRepository.findAll()
                .stream().filter(set -> set.getType() == SetType.EXPANSION)
                .toList();
    }
}
