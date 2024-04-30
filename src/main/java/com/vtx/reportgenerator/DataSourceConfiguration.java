package com.vtx.reportgenerator;

import net.sf.jasperreports.export.ExporterInput;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

public class DataSourceConfiguration extends AbstractJRJDBCConfiguration {
    private String username;
    private String password;
    private String url;

    public DataSourceConfiguration(String username, String password, String url) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        Objects.requireNonNull(url);
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public DataSourceConfiguration() {
    }

    @Override
    public ExporterInput getExporterInput() {
        try {

            DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder
                    .create();

            dataSourceBuilder.username(username);
            dataSourceBuilder.url(url);
            dataSourceBuilder.password(password);

            DataSource dataSource = dataSourceBuilder.build();
            setConnection(dataSource.getConnection());

            return enhanceExporterInput();
        } catch (SQLException exception) {
            String msg = "An error occurred during set up connection";
            logger.error(msg, exception);
            throw new ReportException(msg, exception, 500);
        }
    }
}
