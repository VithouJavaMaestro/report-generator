package com.vtx.reportgenerator;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.catalina.util.ParameterMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Abstract implements JRConfiguration {

    protected static final Log logger = LogFactory.getLog(Abstract.class);
    protected DateFormat dateFormat = DEFAULT_DATE_FORMAT;
    protected NumberFormat numberFormat = DEFAULT_NUMBER_FORMAT;
    protected String zoneId = DEFAULT_TIME_ZONE;
    protected Locale locale = DEFAULT_LOCALE;
    protected String datePattern = DEFAULT_DATE_PATTERN;
    protected String numberPattern = DEFAULT_NUMBER_PATTERN;
    protected String localCode = DEFAULT_LOCALE_CODE;
    private List<JRXMLTemplate> jrXmlTemplates;
    private ParameterMap<String, Object> params;

    protected JasperPrintItemExporter processJasperReportTemplates() {

        try {

            JasperPrintItemExporter jasperPrintItemExporter = new JasperPrintItemExporter();

            if (jrXmlTemplates != null) {

                for (JRXMLTemplate jrXmlTemplate : jrXmlTemplates) {

                    JasperReport jasperReport = JasperCompileManager.compileReport(jrXmlTemplate.getJrXml());
                    jasperReport.setWhenNoDataType(jrXmlTemplate.getWhenNoDataTypeEnum());
                    jasperReport.setSectionType(jrXmlTemplate.getSectionTypeEnum());
                    jasperReport.setWhenResourceMissingType(jrXmlTemplate.getResourceMissingTypeEnum());
                    jasperReport.setJasperReportsContext(jrXmlTemplate.getJasperReportsContext());

                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, this.params);
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


    public void setJrXmlTemplates(List<JRXMLTemplate> jrXmlTemplates) {
        this.jrXmlTemplates = jrXmlTemplates;
    }

    public void setValue(String key, Object value) {
        if (params == null) {
            this.params = new ParameterMap<>();
        }
        this.params.put(key, value);
    }

    public Object getValue(String key) {
        if (this.params.containsKey(key)) {
            return this.params.get(key);
        }
        return null;
    }

    public void addJrXml(JRXMLTemplate jrXml) {
        if (jrXmlTemplates == null) {
            this.jrXmlTemplates = new ArrayList<>();
        }
        this.jrXmlTemplates.add(jrXml);
    }

    public List<JRXMLTemplate> getJrXmlTemplates() {
        return jrXmlTemplates;
    }

    public ParameterMap<String, Object> getParams() {
        return params;
    }

    public void setParams(ParameterMap<String, Object> params) {
        this.params = params;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    public String getNumberPattern() {
        return numberPattern;
    }

    public void setNumberPattern(String numberPattern) {
        this.numberPattern = numberPattern;
    }
}
