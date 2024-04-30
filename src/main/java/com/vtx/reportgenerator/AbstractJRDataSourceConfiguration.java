package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.export.ExporterInput;

public abstract class AbstractJRDataSourceConfiguration extends AbstractJRConfiguration {
    private JRDataSource jrDataSource;

    public void setJrDataSource(JRDataSource jrDataSource) {
        this.jrDataSource = jrDataSource;
    }

    protected ExporterInput processJRDataSource() {
        return processJasperReportTemplates(jasperReport -> JasperFillManager.fillReport(jasperReport, parameters, jrDataSource));
    }
}
