package de.andwari.memory.backend.db.repository;

import de.andwari.memory.backend.db.entity.ShapeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShapeRepository extends JpaRepository<ShapeEntity, Long> {
}
