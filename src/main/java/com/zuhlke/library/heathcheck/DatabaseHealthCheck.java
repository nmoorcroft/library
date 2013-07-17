package com.zuhlke.library.heathcheck;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.yammer.metrics.core.HealthCheck;
import com.zuhlke.library.LibraryConfiguration;

@Component
public class DatabaseHealthCheck extends HealthCheck {

    @Inject
    private LibraryConfiguration configuration;
    
    @Inject
    private DataSource dataSource;
   
    public DatabaseHealthCheck() {
        super("database");
    }
    
    @Override
    protected Result check() throws Exception {
        String validationQuery = configuration.getDatabaseConfiguration().getValidationQuery();
        try (Connection conn = dataSource.getConnection()) {
            conn.createStatement().execute(validationQuery);
            return Result.healthy();
        
        } catch (SQLException e) {
            return Result.unhealthy(e);
        }

    }
    
    
}
