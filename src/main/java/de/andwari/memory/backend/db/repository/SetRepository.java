package de.andwari.memory.backend.db.repository;

import de.andwari.memory.backend.db.entity.SetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<SetEntity, Long> {

}
