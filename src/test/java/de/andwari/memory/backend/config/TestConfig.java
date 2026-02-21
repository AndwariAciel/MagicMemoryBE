package de.andwari.memory.backend.config;

import static org.mockito.Mockito.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.TaskScheduler;

@TestConfiguration
@ComponentScan("de.andwari.memory.backend.web.client")
public class TestConfig {

    @Bean
    @Primary
    public TaskScheduler taskScheduler() {
        return mock(TaskScheduler.class);
    }
}
