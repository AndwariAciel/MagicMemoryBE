package de.andwari.memory.backend.scheduler.db.repository;

import de.andwari.memory.backend.scheduler.db.entity.TaskEntity;
import de.andwari.memory.backend.scheduler.db.entity.TaskStatus;
import de.andwari.memory.backend.scheduler.task.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    Optional<TaskEntity> findByTask(Task task);

    List<TaskEntity> findAllByStatus(TaskStatus status);
}
