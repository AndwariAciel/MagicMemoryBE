package de.andwari.memory.backend.db.repository;

import de.andwari.memory.backend.db.entity.CardEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findByScryfallId(String scryfallId);

}
