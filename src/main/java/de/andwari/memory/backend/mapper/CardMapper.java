package de.andwari.memory.backend.mapper;

import de.andwari.memory.backend.db.entity.CardEntity;
import de.andwari.memory.backend.db.entity.SetEntity;
import de.andwari.memory.backend.model.enums.CardType;
import de.andwari.memory.backend.model.scryfall.cards.CardData;
import de.andwari.memory.backend.service.CardTypeService;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Setter
@Mapper(componentModel = SPRING,
        uses = CardLayoutMapper.class)
public abstract class CardMapper {

    @Autowired
    private CardTypeService cardTypeService;


    @Mapping(target = "name", source = "card.name")
    @Mapping(target = "scryfallId", source = "card.id")
    @Mapping(target = "cardType", source = "card.typeLine")
    @Mapping(target = "cardLayout", source = "card.layout")
    @Mapping(target = "pictureUri", source = "card.imageUris.normal")
    @Mapping(target = "set", source = "set")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    public abstract CardEntity toEntity(CardData card, SetEntity set);

    protected CardType getCardType(String cardType) {
        return cardTypeService.convertCardType(cardType);
    }
}
