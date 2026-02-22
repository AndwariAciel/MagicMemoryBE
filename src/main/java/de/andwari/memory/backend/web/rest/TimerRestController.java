package de.andwari.memory.backend.web.rest;

import de.andwari.memory.backend.model.rest.TimerModel;
import de.andwari.memory.backend.scheduler.SchedulerService;
import de.andwari.memory.backend.scheduler.task.Task;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("timer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TimerRestController {

    private final SchedulerService schedulerService;

    @PutMapping("/start/{task}")
    public void start(@PathVariable Task task) {
        log.info("Task {} started", task);
        schedulerService.start(task);
    }

    @PutMapping("/stop/{task}")
    public void stop(@PathVariable Task task) {
        log.info("Task {} stopped", task);
        schedulerService.stop(task);
    }

    @PutMapping("/execute/{task}")
    public void execute(@PathVariable Task task) {
        log.info("Task {} executed", task);
        schedulerService.execute(task);
    }

    @GetMapping(produces = { "application/json" })
    public List<TimerModel> getTasks() {
        return schedulerService.getTimers();
    }

}
