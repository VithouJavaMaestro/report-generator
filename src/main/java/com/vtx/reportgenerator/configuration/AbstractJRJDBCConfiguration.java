package com.vtx.reportgenerator.configuration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import java.sql.Connection;
import java.util.Objects;

public abstract class AbstractJRJDBCConfiguration extends AbstractJRConfiguration {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected JasperPrint prepareJasperPrint(JasperReport jasperReport) throws JRException {
        Objects.requireNonNull(connection);
        return JasperFillManager.fillReport(jasperReport, parameters, connection);
    }
}
