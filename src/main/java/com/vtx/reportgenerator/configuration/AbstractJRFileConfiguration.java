package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.JrxmlTemplateCustomizer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Abstract class for jasper report related file configuration such as csv, json, xml or xlsx.
 *
 * @author Chanthavithou THEN
 * @see <a href="https://github.com/VithouJavaMaestro">https://github.com/VithouJavaMaestro</a>
 * @since 1.0.0
 */
public abstract class AbstractJRFileConfiguration extends AbstractJRConfiguration {
    protected InputStream importedFile;
    protected DateFormat dateFormat = DEFAULT_DATE_FORMAT;
    protected NumberFormat numberFormat = DEFAULT_NUMBER_FORMAT;
    protected String zoneId = DEFAULT_TIME_ZONE;
    protected Locale locale = DEFAULT_LOCALE;
    protected String datePattern = DEFAULT_DATE_PATTERN;
    protected String numberPattern = DEFAULT_NUMBER_PATTERN;
    protected String localCode = DEFAULT_LOCALE_CODE;

    protected AbstractJRFileConfiguration(InputStream inputStream) {
        this.importedFile = inputStream;
    }

    @Override
    protected JasperPrint prepareJasperPrint(JasperReport jasperReport) throws JRException {
        return JasperFillManager.fillReport(jasperReport, parameters);
    }

    public abstract void prepareJasperDesignAndQueryInternal(JasperDesign jasperDesign, JRDesignQuery jrDesignQuery);

    @Override
    protected void afterPropertiesSet() throws Exception {
        setJxmlTemplateCustomizer(new JrxmlTemplateCustomizer() {
            @Override
            public JasperReport customize(InputStream jrxml) throws JRException {
                JasperDesign jasperDesign = JRXmlLoader.load(jrxml);
                JRDesignQuery jrDesignQuery = new JRDesignQuery();
                jrDesignQuery.setLanguage(getKey().getName());
                jasperDesign.setQuery(jrDesignQuery);
                prepareJasperDesignAndQueryInternal(jasperDesign, jrDesignQuery);
                return JasperCompileManager.compileReport(jasperDesign);
            }
        });
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

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }
}
