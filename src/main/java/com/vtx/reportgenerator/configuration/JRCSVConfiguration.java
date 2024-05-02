package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.key.JRFileConfigurationKey;
import com.vtx.reportgenerator.key.Key;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.query.JRCsvQueryExecuterFactory;

import java.io.InputStream;
import java.util.TimeZone;

public class JRCSVConfiguration extends AbstractJRFileConfiguration {
    private String csvEncoding = "UTF-8";
    private String csvDelimiter;
    private boolean useFirstRowAsHeader;

    public JRCSVConfiguration(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    protected void afterPropertiesSet() throws Exception {

        super.afterPropertiesSet();

        setValue(JRCsvQueryExecuterFactory.CSV_LOCALE_CODE, localCode);
        setValue(JRCsvQueryExecuterFactory.CSV_ENCODING, csvEncoding);
        setValue(JRCsvQueryExecuterFactory.CSV_LOCALE, locale);
        setValue(JRCsvQueryExecuterFactory.CSV_DATE_FORMAT, dateFormat);
        setValue(JRCsvQueryExecuterFactory.CSV_TIMEZONE, TimeZone.getTimeZone(zoneId));
        setValue(JRCsvQueryExecuterFactory.CSV_DATE_PATTERN, datePattern);
        setValue(JRCsvQueryExecuterFactory.CSV_NUMBER_FORMAT, numberFormat);
        setValue(JRCsvQueryExecuterFactory.CSV_NUMBER_PATTERN, numberPattern);
        setValue(JRCsvQueryExecuterFactory.CSV_INPUT_STREAM, importedFile);
        setValue(JRCsvQueryExecuterFactory.CSV_USE_FIRST_ROW_AS_HEADER, useFirstRowAsHeader);

    }

    @Override
    public void prepareJasperDesignAndQueryInternal(JasperDesign jasperDesign, JRDesignQuery jrDesignQuery) {

    }

    @Override
    public Key getKey() {
        return JRFileConfigurationKey.CSV;
    }

    public String getCsvEncoding() {
        return csvEncoding;
    }

    public void setCsvEncoding(String csvEncoding) {
        this.csvEncoding = csvEncoding;
    }

    public String getCsvDelimiter() {
        return csvDelimiter;
    }

    public void setCsvDelimiter(String csvDelimiter) {
        this.csvDelimiter = csvDelimiter;
    }

    public boolean isUseFirstRowAsHeader() {
        return useFirstRowAsHeader;
    }

    public void setUseFirstRowAsHeader(boolean useFirstRowAsHeader) {
        this.useFirstRowAsHeader = useFirstRowAsHeader;
    }

}
