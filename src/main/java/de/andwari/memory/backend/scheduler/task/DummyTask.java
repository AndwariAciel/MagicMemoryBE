package de.andwari.memory.backend.scheduler.task;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class DummyTask implements ScheduledTask {

    @Override
    public void run() {
        log.info("Running dummy task");
    }

    @Override
    public Task getName() {
        return Task.DUMMY;
    }

    @Override
    public String getDefaultCron() {
        return "0/10 * * * * *";
    }
}
