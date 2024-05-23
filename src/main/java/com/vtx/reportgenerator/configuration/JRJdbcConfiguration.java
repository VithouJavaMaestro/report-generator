package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.ReportException;
import com.vtx.reportgenerator.key.JRConfigurationKey;
import com.vtx.reportgenerator.key.Key;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

public class JRJdbcConfiguration extends AbstractJRJDBCConfiguration {

    private final String username;
    private final String password;
    private final String url;

    public JRJdbcConfiguration(String username, String password, String url) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        Objects.requireNonNull(url);
        this.username = username;
        this.password = password;
        this.url = url;
    }

    @Override
    protected void afterPropertiesSet() {

        try {

            DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder
                    .create();

            dataSourceBuilder.username(username);
            dataSourceBuilder.url(url);
            dataSourceBuilder.password(password);

            DataSource dataSource = dataSourceBuilder.build();
            setConnection(dataSource.getConnection());

        } catch (SQLException exception) {
            String msg = "An error occurred during set up connection";
            logger.error(msg, exception);
            throw new ReportException(msg, exception, 500);
        }
    }

    @Override
    public Key getKey() {
        return JRConfigurationKey.JR_JDBC;
    }
}
