package de.andwari.memory.backend.scheduler.task;

import static de.andwari.memory.backend.scheduler.task.Tasks.GET_SETS;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class GetSetsTask implements ScheduledTask {

    @Override
    public void run() {
        log.info("TICKED!");
    }

    @Override
    public Tasks getName() {
        return GET_SETS;
    }
}
