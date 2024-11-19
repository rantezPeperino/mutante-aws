package com.xmen.mutante.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.xmen.mutante.repository.DatabaseHealthRepository;

@Component
public class MySqlHealthIndicator implements HealthIndicator {
    
    @Autowired
    private DatabaseHealthRepository  repository;

    @Override
    public Health health() {
        try {
            if (repository.checkConnection()) {
                String version = repository.getDatabaseVersion();
                return Health.up()
                    .withDetail("status", "UP")
                    .withDetail("database", "MySQL")
                    .withDetail("version", version != null ? version : "Unknown")
                    .build();
            }
            return Health.down()
                    .withDetail("status", "DOWN")
                    .withDetail("error", "MySQL connection failed")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("status", "DOWN")
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}