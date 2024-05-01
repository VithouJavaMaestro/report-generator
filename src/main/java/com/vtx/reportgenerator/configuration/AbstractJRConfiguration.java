package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.JasperReportCustomizer;
import com.vtx.reportgenerator.JasperPrintItemExporter;
import com.vtx.reportgenerator.JxmlTemplateCustomizer;
import com.vtx.reportgenerator.ReportException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.export.ExporterInput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractJRConfiguration implements JRConfiguration {
    protected static final Log logger = LogFactory.getLog(AbstractJRConfiguration.class);
    protected JxmlTemplateCustomizer jxmlTemplateCustomizer;
    protected JasperReportCustomizer jasperReportCustomizer;
    protected List<InputStream> jrXmlTemplates;
    protected Map<String, Object> parameters;
    protected JasperReportsContext jasperReportsContext = new SimpleJasperReportsContext();

    protected abstract JasperPrint initializeJasperPrint(JasperReport jasperReport) throws JRException;

    protected abstract void afterPropertiesSet();

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
                    JasperPrint jasperPrint = initializeJasperPrint(jasperReport);
                    if (jasperReportCustomizer != null) {
                        jasperReportCustomizer.customize(jasperPrint);
                    }
                    jasperPrintItemExporter.add(jasperPrint);

                }
            }

            return jasperPrintItemExporter;

        } catch (JRException exception) {
            if (logger.isErrorEnabled()) {
                logger.error("An error occurred during process report", exception);
            }
            throw new ReportException("An error occurred during process report", exception, 500);
        }
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        Object value = jasperReportsContext.getValue(key);
        if (value != null) {
            return clazz.cast(value);
        }
        return null;
    }

    @Override
    public void put(String key, Object value) {
        jasperReportsContext.setValue(key, value);
    }


    public void setJxmlTemplateCustomizer(JxmlTemplateCustomizer jxmlTemplateCustomizer) {
        this.jxmlTemplateCustomizer = jxmlTemplateCustomizer;
    }

    public void setJasperReportCustomizer(JasperReportCustomizer jasperReportCustomizer) {
        this.jasperReportCustomizer = jasperReportCustomizer;
    }

    public void setJrXmlTemplates(List<InputStream> jrXmlTemplates) {
        this.jrXmlTemplates = jrXmlTemplates;
    }

    public void setValue(String key, Object value) {
        if (this.parameters == null) {
            this.parameters = new HashMap<>();
        }
        this.parameters.put(key, value);
    }


    public void addJrXml(InputStream jrXml) {
        if (jrXmlTemplates == null) {
            this.jrXmlTemplates = new ArrayList<>();
        }
        this.jrXmlTemplates.add(jrXml);
    }

    public List<InputStream> getJrXmlTemplates() {
        return jrXmlTemplates;
    }

    public Map<String, Object> getParameters() {
        return this.parameters;
    }
}
