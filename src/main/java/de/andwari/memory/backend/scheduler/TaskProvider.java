package de.andwari.memory.backend.scheduler;

import de.andwari.memory.backend.scheduler.task.DummyTask;
import de.andwari.memory.backend.scheduler.task.GetSetsTask;
import de.andwari.memory.backend.scheduler.task.ScheduledTask;
import de.andwari.memory.backend.scheduler.task.Task;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskProvider {

    private final GetSetsTask getSetsTask;
    private final DummyTask dummyTask;

    private static final Map<Task, ScheduledTask> TASKS = new HashMap<>();

    @PostConstruct
    private void init() {
        TASKS.put(getSetsTask.getName(), getSetsTask);
        TASKS.put(dummyTask.getName(), dummyTask);
    }

    public ScheduledTask getTask(Task name) {
        return TASKS.get(name);
    }

    public Map<Task, ScheduledTask> getTasks() {
        return TASKS;
    }
}
