package de.andwari.memory.backend.web.rest;

import de.andwari.memory.backend.scheduler.SchedulerService;
import de.andwari.memory.backend.scheduler.task.Tasks;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("timer")
@RequiredArgsConstructor
public class TimerRestController {

    private final SchedulerService schedulerService;

    @PutMapping("/start/{task}")
    public void start(@PathVariable Tasks task) {
        schedulerService.start(task);
    }

    @PutMapping("/stop/{task}")
    public void stop(@PathVariable Tasks task) {
        schedulerService.stop(task);
    }

}
