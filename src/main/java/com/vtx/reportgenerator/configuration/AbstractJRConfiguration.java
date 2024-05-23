package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.JasperPrintCustomizer;
import com.vtx.reportgenerator.JasperPrintItemExporter;
import com.vtx.reportgenerator.JrxmlTemplateCustomizer;
import com.vtx.reportgenerator.ReportException;
import com.vtx.reportgenerator.key.ExporterKey;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.export.ExporterInput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract class for jasper report configuration.
 *
 * @author Chanthavithou THEN
 * @see JRJsonConfiguration
 * @see JRXlsxConfiguration
 * @see JRXmlConfiguration
 * @see JRCSVConfiguration
 * @see <a href="https://github.com/VithouJavaMaestro">https://github.com/VithouJavaMaestro</a>
 * @since 1.0.0
 */
public abstract class AbstractJRConfiguration implements JRConfiguration {

    protected static final Log logger = LogFactory.getLog(AbstractJRConfiguration.class);
    /**
     * jrxmlTemplateCustomizer for customizing jrxmlTemplate input stream.
     */
    protected JrxmlTemplateCustomizer jxmlTemplateCustomizer;
    /**
     * jasperReportCustomizer for customizing jasperPrint instance.
     */
    protected JasperPrintCustomizer jasperPrintCustomizer;
    /**
     * jrxmlTemplate for importing template of jrxml.
     */
    protected InputStream jrXmlTemplate;
    /**
     * parameters for jasperReport.
     */
    protected Map<String, Object> parameters;
    /**
     * Context for additional properties depends on use cases.
     */
    protected JasperReportsContext jasperReportsContext = new SimpleJasperReportsContext();

    private ExporterKey exporterKey;

    /**
     * {@link JasperPrint} the preparation for different configurations.
     *
     * @param jasperReport refers to {@link JasperReport}.
     * @return {@link JasperPrint} that configured from concrete class implemented it.
     * @throws JRException when errors occurred during process the reports.
     * @see AbstractJRFileConfiguration
     * @see AbstractJRDataSourceConfiguration
     * @see AbstractJRJDBCConfiguration
     */
    protected abstract JasperPrint prepareJasperPrint(JasperReport jasperReport) throws JRException;

    /**
     * Additional configuration.
     */
    protected abstract void afterPropertiesSet() throws Exception;

    /**
     * retrieve {@link ExporterInput} that need to consume in {@link ExporterInput}.
     *
     * @return ExporterInput
     */
    @Override
    public ExporterInput getExporterInput() {

        try {

            JasperPrintItemExporter jasperPrintItemExporter = new JasperPrintItemExporter();

            if (jrXmlTemplate != null) {

                afterPropertiesSet();

                    JasperReport jasperReport;
                    if (jxmlTemplateCustomizer == null) {
                        jasperReport = JasperCompileManager.compileReport(jrXmlTemplate);
                    } else {
                        jasperReport = jxmlTemplateCustomizer.customize(jrXmlTemplate);
                    }
                    JasperPrint jasperPrint = prepareJasperPrint(jasperReport);
                    if (jasperPrintCustomizer != null) {
                        jasperPrintCustomizer.customize(jasperPrint);
                    }
                    jasperPrintItemExporter.add(jasperPrint);
            }

            return jasperPrintItemExporter;

        } catch (Exception exception) {
            if (logger.isErrorEnabled()) {
                logger.error("An error occurred during process report", exception);
            }
            throw new ReportException("An error occurred during process report", exception, 500);
        }
    }

    public JasperReportsContext getContext() {
        return jasperReportsContext;
    }

    /**
     * @param jrxmlTemplateCustomizer stands for a customizer customizing jrXmlTemplate inputstream.
     */
    public void setJxmlTemplateCustomizer(JrxmlTemplateCustomizer jrxmlTemplateCustomizer) {
        this.jxmlTemplateCustomizer = jrxmlTemplateCustomizer;
    }

    /**
     * @param jasperReportCustomizer stands for customizer customizing {@link JasperPrint}.
     */
    public void setJasperReportCustomizer(JasperPrintCustomizer jasperReportCustomizer) {
        this.jasperPrintCustomizer = jasperReportCustomizer;
    }


    /**
     * @param key   stands for key needs to put in jasperReports.
     * @param value stands for value needs to put in jasperReports.
     */
    public void setValue(String key, Object value) {
        if (this.parameters == null) {
            this.parameters = new HashMap<>();
        }
        this.parameters.put(key, value);
    }

    public void setJrXmlTemplate(InputStream jrXmlTemplate) {
        this.jrXmlTemplate = jrXmlTemplate;
    }

    /**
     * Retrieve to configuration parameters.
     *
     * @return {@link Map}
     */
    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    public void setExporterKey(ExporterKey exporterKey) {
        this.exporterKey = exporterKey;
    }

    public ExporterKey getExportationKey() {
        return exporterKey;
    }
}
