package de.andwari.memory.backend.config;

import org.junit.jupiter.api.extension.Extension;
import org.testcontainers.containers.PostgreSQLContainer;

public class DockerContainerExtension implements Extension {

    static {
        var postgres = new PostgreSQLContainer<>("postgres:14.4");
        postgres.start();

        System.setProperty("SQL_DB_URL", postgres.getJdbcUrl());
        System.setProperty("SQL_DB_DRIVER", postgres.getDriverClassName());
        System.setProperty("SQL_DB_USERNAME", postgres.getUsername());
        System.setProperty("SQL_DB_PASSWORD", postgres.getPassword());
    }

}
