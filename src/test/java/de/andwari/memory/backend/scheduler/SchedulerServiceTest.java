package de.andwari.memory.backend.scheduler;

import static de.andwari.memory.backend.scheduler.task.Task.GET_SETS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import de.andwari.memory.backend.TestApplication;
import de.andwari.memory.backend.config.TestConfig;
import de.andwari.memory.backend.scheduler.db.entity.TaskStatus;
import de.andwari.memory.backend.scheduler.db.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@Import(TestConfig.class)
class SchedulerServiceTest {

    @Container
    static PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
            new PostgreSQLContainer<>("postgres:14.4")
                    .withDatabaseName("memory_backend")
                    .withUsername("postgres")
                    .withPassword("password");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
    }


    @MockitoSpyBean
    private TaskScheduler taskScheduler;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskProvider taskProvider;
    @Autowired
    private SchedulerService schedulerService;


    @Test
    void testInitializeEmpty() {

        var task = taskRepository.findByTask(GET_SETS);
        if (task.isEmpty()) {
            fail();
        }
        assertEquals(GET_SETS, task.get().getTask());
        assertEquals(TaskStatus.INACTIVE, task.get().getStatus());
    }

    @Test
    void testInitializeAlreadyExists() {

        // Second invocation, should not create a new task, there is already one
        new SchedulerService(taskProvider, taskRepository);
        // Would fail if the task was created again
        taskRepository.findByTask(GET_SETS);
    }

    @Test
    void testUpdateValidCron() {
        schedulerService.update(GET_SETS, "0 0/1 * * * ?");
        var task = taskRepository.findByTask(GET_SETS);
        if (task.isEmpty()) {
            fail();
        }
        assertEquals("0 0/1 * * * ?", task.get().getCron());
    }

    @Test
    void testUpdateInvalidCron() {
        assertThrows(IllegalArgumentException.class, () ->
                schedulerService.update(GET_SETS, "what is this?")
        );

        var task = taskRepository.findByTask(GET_SETS);
        if (task.isEmpty()) {
            fail();
        }
        assertEquals("0 0/1 * * * ?", task.get().getCron());
    }

}