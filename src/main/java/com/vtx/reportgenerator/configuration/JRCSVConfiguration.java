package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.utils.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.TimeZone;
import net.sf.jasperreports.engine.query.JRCsvQueryExecuterFactory;

public class JRCSVConfiguration extends AbstractJRFileConfiguration {
    private String csvEncoding = "UTF-8";
    private String csvDelimiter;
    private boolean useFirstRowAsHeader;

    public JRCSVConfiguration(String csvPath) {
        super(csvPath);
        FileUtils.checkFileExtension(csvPath, "csv");
    }

    @Override
    protected void afterPropertiesSet() throws FileNotFoundException {

        setValue(JRCsvQueryExecuterFactory.CSV_LOCALE_CODE, localCode);
        setValue(JRCsvQueryExecuterFactory.CSV_ENCODING, csvEncoding);
        setValue(JRCsvQueryExecuterFactory.CSV_FILE, new File(path));
        setValue(JRCsvQueryExecuterFactory.CSV_LOCALE, locale);
        setValue(JRCsvQueryExecuterFactory.CSV_DATE_FORMAT, dateFormat);
        setValue(JRCsvQueryExecuterFactory.CSV_SOURCE, path);
        setValue(JRCsvQueryExecuterFactory.CSV_TIMEZONE, TimeZone.getTimeZone(zoneId));
        setValue(JRCsvQueryExecuterFactory.CSV_DATE_PATTERN, datePattern);
        setValue(JRCsvQueryExecuterFactory.CSV_NUMBER_FORMAT, numberFormat);
        setValue(JRCsvQueryExecuterFactory.CSV_NUMBER_PATTERN, numberPattern);
        setValue(JRCsvQueryExecuterFactory.CSV_INPUT_STREAM, new FileInputStream(path));
        setValue(JRCsvQueryExecuterFactory.CSV_USE_FIRST_ROW_AS_HEADER, useFirstRowAsHeader);
        setValue(JRCsvQueryExecuterFactory.CSV_READER, new FileReader(path));

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
