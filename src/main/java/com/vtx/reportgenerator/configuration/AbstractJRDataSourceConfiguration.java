package com.vtx.reportgenerator.configuration;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public abstract class AbstractJRDataSourceConfiguration extends AbstractJRConfiguration {
    private JRDataSource jrDataSource;

    public void setJrDataSource(JRDataSource jrDataSource) {
        this.jrDataSource = jrDataSource;
    }

    @Override
    protected JasperPrint initializeJasperPrint(JasperReport jasperReport) throws JRException {
        return JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
    }
}
