package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.JasperPrintCustomizer;
import com.vtx.reportgenerator.JasperPrintItemExporter;
import com.vtx.reportgenerator.JrxmlTemplateCustomizer;
import com.vtx.reportgenerator.ReportException;
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
     * List of jrxmlTemplate for importing multiple templates of jrxml.
     */
    protected List<InputStream> jrXmlTemplates;
    /**
     * parameters for jasperReport.
     */
    protected Map<String, Object> parameters;
    /**
     * Context for additional properties depends on use cases.
     */
    protected JasperReportsContext jasperReportsContext = new SimpleJasperReportsContext();

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
     * retrieve {@link ExporterInput} that need to consume in {@link com.vtx.reportgenerator.ReportExportationProvider}.
     *
     * @return ExporterInput
     */
    @Override
    public ExporterInput getExporterInput() {

        try {

            JasperPrintItemExporter jasperPrintItemExporter = new JasperPrintItemExporter();

            if (jrXmlTemplates != null) {

                afterPropertiesSet();

                for (InputStream jrXmlTemplate : jrXmlTemplates) {

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
            }

            return jasperPrintItemExporter;

        } catch (Exception exception) {
            if (logger.isErrorEnabled()) {
                logger.error("An error occurred during process report", exception);
            }
            throw new ReportException("An error occurred during process report", exception, 500);
        }
    }

    /**
     * Retrieve the value by key and type.
     *
     * @param key   stands for key in Map.
     * @param clazz stands for class type.
     * @param <T>   type for response.
     */
    @Override
    public <T> T get(String key, Class<T> clazz) {
        Object value = jasperReportsContext.getValue(key);
        if (value != null) {
            return clazz.cast(value);
        }
        return null;
    }

    /**
     * Set value in Map by providing key and any value.
     *
     * @param key   stands for key in Map.
     * @param value stands for any value.
     */
    @Override
    public void put(String key, Object value) {
        jasperReportsContext.setValue(key, value);
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
     * @param jrXmlTemplates stands for jrxmlTemplate as inputStream.
     */
    public void setJrXmlTemplates(List<InputStream> jrXmlTemplates) {
        this.jrXmlTemplates = jrXmlTemplates;
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

    /**
     * @param jrXml stands for jrxmlTemplate as inputStream.
     */
    public void addJrXml(InputStream jrXml) {
        if (jrXmlTemplates == null) {
            this.jrXmlTemplates = new ArrayList<>();
        }
        this.jrXmlTemplates.add(jrXml);
    }

    /**
     * Retrieve to configuration parameters.
     * @return {@link Map}
     */
    public Map<String, Object> getParameters() {
        return this.parameters;
    }
}
