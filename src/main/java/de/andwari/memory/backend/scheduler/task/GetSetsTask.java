package de.andwari.memory.backend.scheduler.task;

import static de.andwari.memory.backend.scheduler.task.Task.GET_SETS;

import de.andwari.memory.backend.service.SetService;
import de.andwari.memory.backend.service.SetUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class GetSetsTask implements ScheduledTask {

    private final SetUpdateService setUpdateService;

    @Override
    public void run() {
        setUpdateService.updateSets();
    }

    @Override
    public Task getName() {
        return GET_SETS;
    }

    @Override
    public String getDefaultCron() {
        return "0/10 * * * * *";
    }
}
