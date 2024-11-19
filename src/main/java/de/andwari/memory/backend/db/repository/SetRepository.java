package de.andwari.memory.backend.db.repository;

import de.andwari.memory.backend.db.entity.SetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetRepository extends JpaRepository<SetEntity, Long> {

    Optional<SetEntity> findByScryfallId(String scryfallId);
    Optional<SetEntity> findByCode(String code);

}
