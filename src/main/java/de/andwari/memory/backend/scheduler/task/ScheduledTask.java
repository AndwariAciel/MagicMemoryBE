package de.andwari.memory.backend.scheduler.task;

public interface ScheduledTask extends Runnable {

    void run();

    Tasks getName();
}
