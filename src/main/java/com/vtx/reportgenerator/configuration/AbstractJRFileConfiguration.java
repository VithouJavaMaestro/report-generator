package com.vtx.reportgenerator.configuration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class AbstractJRFileConfiguration extends AbstractJRConfiguration {
    protected DateFormat dateFormat = DEFAULT_DATE_FORMAT;
    protected NumberFormat numberFormat = DEFAULT_NUMBER_FORMAT;
    protected String zoneId = DEFAULT_TIME_ZONE;
    protected Locale locale = DEFAULT_LOCALE;
    protected String datePattern = DEFAULT_DATE_PATTERN;
    protected String numberPattern = DEFAULT_NUMBER_PATTERN;
    protected String localCode = DEFAULT_LOCALE_CODE;

    @Override
    protected JasperPrint initializeJasperPrint(JasperReport jasperReport) throws JRException {
        return JasperFillManager.fillReport(jasperReport, parameters);
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
