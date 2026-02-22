package de.andwari.memory.backend.scheduler.task;

public interface ScheduledTask extends Runnable {

    Task getName();

    String getDefaultCron();
}
