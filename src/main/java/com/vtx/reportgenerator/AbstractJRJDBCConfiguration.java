package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.export.ExporterInput;

import java.sql.Connection;

public class AbstractJRJDBCConfiguration extends AbstractJRConfiguration {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ExporterInput enhanceExporterInput() {
        return processJasperReportTemplates(jasperReport ->
                JasperFillManager.fillReport(jasperReport, parameters, connection));
    }
}
