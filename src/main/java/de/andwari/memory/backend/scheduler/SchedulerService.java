package de.andwari.memory.backend.scheduler;

import static java.util.Optional.ofNullable;

import de.andwari.memory.backend.scheduler.task.Tasks;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    private final TaskScheduler taskScheduler;
    private final TaskProvider taskProvider;

    private final Map<Tasks, AtomicReference<ScheduledFuture<?>>> scheduledTasks = new HashMap<>();

    private static final String CRON = "0/10 * * * * *";


    public SchedulerService(TaskProvider taskProvider) {
        var scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.initialize();
        taskScheduler = scheduler;
        this.taskProvider = taskProvider;

        this.taskProvider.getTasks().values()
                .forEach(task ->
                        scheduledTasks.put(
                                task.getName(),
                                new AtomicReference<>(taskScheduler.schedule(task, new CronTrigger(CRON))))
                );
    }

    public void start(Tasks name) {
        stop(name);
        Trigger trigger = new CronTrigger(CRON);
        scheduledTasks.get(name)
                .set(taskScheduler.schedule(taskProvider.getTask(name), trigger));
    }

    public void stop(Tasks name) {
        ofNullable(scheduledTasks.get(name))
                .map(future -> future.getAndSet(null))
                .ifPresent(task -> task.cancel(false));
    }

}
