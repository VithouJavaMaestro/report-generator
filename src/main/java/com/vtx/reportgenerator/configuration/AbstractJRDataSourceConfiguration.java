package com.vtx.reportgenerator.configuration;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.AbstractXmlDataSource;

/**
 * Abstract class for datasource configuration.
 *
 * @author Chanthavithou THEN
 * @since 1.0.0
 * @see <a href="https://github.com/VithouJavaMaestro">https://github.com/VithouJavaMaestro</a>
 */
public abstract class AbstractJRDataSourceConfiguration extends AbstractJRConfiguration {
    private JRDataSource jrDataSource;

    public void setJrDataSource(JRDataSource jrDataSource) {
        this.jrDataSource = jrDataSource;
    }

    @Override
    protected JasperPrint prepareJasperPrint(JasperReport jasperReport) throws JRException {
        return JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
    }
}
