package de.andwari.memory.backend.scheduler;

import de.andwari.memory.backend.scheduler.task.GetSetsTask;
import de.andwari.memory.backend.scheduler.task.ScheduledTask;
import de.andwari.memory.backend.scheduler.task.Tasks;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskProvider {

    private final GetSetsTask getSetsTask;

    private static final Map<Tasks, ScheduledTask> TASKS = new HashMap<>();

    @PostConstruct
    private void init() {
        TASKS.put(getSetsTask.getName(), getSetsTask);
    }

    public ScheduledTask getTask(Tasks name) {
        return TASKS.get(name);
    }

    public Map<Tasks, ScheduledTask> getTasks() {
        return TASKS;
    }
}
