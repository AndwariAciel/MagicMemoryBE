package de.andwari.memory.backend.db.repository;

import de.andwari.memory.backend.db.entity.MaskEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaskRepository extends JpaRepository<MaskEntity, Long> {

    Optional<MaskEntity> findByName(String name);
}
