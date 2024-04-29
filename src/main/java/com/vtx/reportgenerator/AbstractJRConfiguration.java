package com.vtx.reportgenerator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractJRConfiguration implements JRConfiguration {
    protected static final Log logger = LogFactory.getLog(AbstractJRConfiguration.class);

    protected JasperReportCustomizer customizer;
    protected List<InputStream> jrXmlTemplates;
    protected Map<String, Object> parameters;

    protected JasperPrintItemExporter processJasperReportTemplates(Function<JasperReport, JasperPrint> jasperPrintFunction) {

        try {

            JasperPrintItemExporter jasperPrintItemExporter = new JasperPrintItemExporter();

            if (jrXmlTemplates != null) {
                for (InputStream jrXmlTemplate : jrXmlTemplates) {
                    JasperReport jasperReport = JasperCompileManager.compileReport(jrXmlTemplate);
                    JasperPrint jasperPrint = jasperPrintFunction.apply(jasperReport);
                    if (customizer != null) {
                        customizer.customize(jasperReport, jasperPrint);
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

    public void setCustomizer(JasperReportCustomizer jasperReportCustomizer) {
        this.customizer = jasperReportCustomizer;
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
