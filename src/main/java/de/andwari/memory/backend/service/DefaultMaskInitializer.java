package de.andwari.memory.backend.service;

import static de.andwari.memory.backend.model.constants.DefaultMasks.DEFAULT_CREATURE_3M;
import static de.andwari.memory.backend.model.constants.DefaultMasks.DEFAULT_SPELL_3M;
import static de.andwari.memory.backend.model.enums.ShapeType.MANA;
import static de.andwari.memory.backend.model.enums.ShapeType.PT;
import static de.andwari.memory.backend.model.enums.ShapeType.TEXT;
import static de.andwari.memory.backend.model.enums.ShapeType.TYPE;

import de.andwari.memory.backend.db.entity.MaskEntity;
import de.andwari.memory.backend.db.entity.ShapeEntity;
import de.andwari.memory.backend.db.repository.MaskRepository;
import de.andwari.memory.backend.model.enums.ShapeType;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultMaskInitializer {

    private final MaskRepository maskRepository;


    @PostConstruct
    public void init() {
        if (maskRepository.count() == 0) {
            var masks = new ArrayList<MaskEntity>();
            masks.add(createDFSpellM3(DEFAULT_SPELL_3M));
            masks.add(createDFSpellM3(DEFAULT_CREATURE_3M));
            maskRepository.saveAll(masks);
        }
    }

    private MaskEntity createDFSpellM3(String mask) {

        return switch (mask) {
            case DEFAULT_SPELL_3M -> MaskEntity.builder()
                    .name(DEFAULT_SPELL_3M)
                    .shapes(List.of(
                            createShape(MANA, 375, 32, 90, 40),
                            createShape(TYPE, 18, 380, 450, 42),
                            createShape(TEXT, 18, 422, 450, 250)))
                    .build();
            case DEFAULT_CREATURE_3M -> MaskEntity.builder()
                    .name(DEFAULT_CREATURE_3M)
                    .shapes(List.of(
                            createShape(MANA, 375, 32, 90, 40),
                            createShape(TYPE, 18, 380, 450, 42),
                            createShape(TEXT, 18, 422, 450, 250),
                            createShape(PT, 365, 602, 100, 48)))
                    .build();
            default -> null;
        };
    }

    private ShapeEntity createShape(ShapeType type, int x, int y, int w, int h) {
        return ShapeEntity.builder()
                .type(type)
                .x(x)
                .y(y)
                .width(w)
                .height(h)
                .build();
    }


}
