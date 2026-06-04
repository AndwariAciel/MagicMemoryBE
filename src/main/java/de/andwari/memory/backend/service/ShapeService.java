package de.andwari.memory.backend.service;

import de.andwari.memory.backend.db.repository.ShapeRepository;
import de.andwari.memory.backend.mapper.CardModelMapper;
import de.andwari.memory.backend.model.rest.ShapeModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShapeService {

    private final ShapeRepository shapeRepository;
    private final CardModelMapper mapper;

    public List<ShapeModel> getAll() {
        return shapeRepository.findAll().stream()
                .map(mapper::toModel)
                .toList();
    }

    public ShapeModel create(ShapeModel shape) {
        var saved = shapeRepository.save(mapper.toEntity(shape));
        return mapper.toModel(saved);
    }
}
