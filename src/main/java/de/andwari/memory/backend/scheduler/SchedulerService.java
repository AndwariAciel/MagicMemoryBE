package de.andwari.memory.backend.scheduler;

import static de.andwari.memory.backend.scheduler.db.entity.TaskStatus.ACTIVE;
import static de.andwari.memory.backend.scheduler.db.entity.TaskStatus.INACTIVE;
import static java.util.Optional.ofNullable;

import de.andwari.memory.backend.mapper.TaskMapper;
import de.andwari.memory.backend.model.rest.TimerModel;
import de.andwari.memory.backend.scheduler.db.entity.TaskEntity;
import de.andwari.memory.backend.scheduler.db.repository.TaskRepository;
import de.andwari.memory.backend.scheduler.task.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    private final TaskScheduler taskScheduler;
    private final TaskProvider taskProvider;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private final Map<Task, AtomicReference<ScheduledFuture<?>>> scheduledTasks = new HashMap<>();


    public SchedulerService(TaskProvider taskProvider, TaskRepository taskRepository, TaskMapper taskMapper) {
        var scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.initialize();
        taskScheduler = scheduler;
        this.taskProvider = taskProvider;
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;

        initializeTasks();
    }

    public void start(Task name) {
        var task = taskRepository.findByTask(name)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + name));

        if (task.getStatus() == INACTIVE) {
            scheduledTasks.put(name,
                    new AtomicReference<>(
                            taskScheduler.schedule(
                                    taskProvider.getTask(name),
                                    new CronTrigger(task.getCron()))));
            task.setStatus(ACTIVE);
            taskRepository.save(task);
        }
    }

    public void execute(Task name) {
        taskProvider.getTask(name).run();
    }

    public void stop(Task name) {
        ofNullable(scheduledTasks.get(name))
                .map(future -> future.getAndSet(null))
                .ifPresent(task -> {
                    task.cancel(false);
                    scheduledTasks.remove(name);
                    taskRepository.findByTask(name)
                            .ifPresent(entity -> {
                                entity.setStatus(INACTIVE);
                                taskRepository.save(entity);
                            });
                });
    }

    public void restart(Task name) {
        stop(name);
        start(name);
    }

    public List<TimerModel> getTimers() {
        return taskRepository.findAll().stream()
                .map(taskMapper::map)
                .toList();
    }

    public void update(Task name, String cron) {
        var task = taskRepository.findByTask(name)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + name));

        if (task.getStatus() == ACTIVE) {
            stop(name);
        }
        // Validate cron:
        new CronTrigger(cron);

        task.setCron(cron);
        taskRepository.save(task);

        if (task.getStatus() == ACTIVE) {
            start(name);
        }
    }

    private void initializeTasks() {
        taskProvider.getTasks().values()
                .forEach(task -> {
                    if (taskRepository.findByTask(task.getName()).isEmpty()) {
                        taskRepository.save(
                                TaskEntity.builder()
                                        .cron(task.getDefaultCron())
                                        .task(task.getName())
                                        .status(INACTIVE)
                                        .build());
                    }
                });

        taskRepository.findAllByStatus(ACTIVE)
                .forEach(task ->
                        scheduledTasks.put(
                                task.getTask(),
                                new AtomicReference<>(
                                        taskScheduler.schedule(
                                                taskProvider.getTask(task.getTask()),
                                                new CronTrigger(task.getCron())))));

    }

}
