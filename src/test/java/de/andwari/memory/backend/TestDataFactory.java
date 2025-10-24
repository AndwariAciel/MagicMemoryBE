package de.andwari.memory.backend;

import de.andwari.memory.backend.db.entity.SetEntity;
import de.andwari.memory.backend.model.enums.SetType;
import de.andwari.memory.backend.model.scryfall.sets.Set;
import de.andwari.memory.backend.model.scryfall.sets.SetsRoot;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class TestDataFactory {

    public static SetsRoot getSetsFromClient() {
        SetsRoot setsRoot = new SetsRoot();
        setsRoot.setHasMore(false);
        setsRoot.setData(new ArrayList<>());

        IntStream.range(0, 3).forEach(i -> {
            Set set = new Set();
            set.setId(String.valueOf(i));
            set.setCode("set-code");
            set.setName("set-name");
            set.setSetType("expansion");
            setsRoot.getData().add(set);
        });

        return setsRoot;
    }

    public static SetEntity getSetEntity(int number, String name) {
        return SetEntity.builder()
                .id((long) number)
                .scryfallId(String.valueOf(number))
                .code("set-code")
                .name(name)
                .type(SetType.EXPANSION)
                .build();
    }
}
