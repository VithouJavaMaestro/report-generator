package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.FileUtils;
import com.vtx.reportgenerator.ReportException;
import net.sf.jasperreports.engine.query.JRCsvQueryExecuterFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.TimeZone;

public class CSVConfiguration extends AbstractJRFileConfiguration {
    private String csvPath;
    private String csvEncoding = "UTF-8";
    private String csvDelimiter;
    private boolean useFirstRowAsHeader;

    public CSVConfiguration(String csvPath) {
        FileUtils.checkFileExtension(csvPath, "csv");
        this.csvPath = csvPath;
    }

    @Override
    protected void afterPropertiesSet() {

        try {

            setValue(JRCsvQueryExecuterFactory.CSV_LOCALE_CODE, localCode);
            setValue(JRCsvQueryExecuterFactory.CSV_ENCODING, csvEncoding);
            setValue(JRCsvQueryExecuterFactory.CSV_FILE, new File(csvPath));
            setValue(JRCsvQueryExecuterFactory.CSV_LOCALE, locale);
            setValue(JRCsvQueryExecuterFactory.CSV_DATE_FORMAT, dateFormat);
            setValue(JRCsvQueryExecuterFactory.CSV_SOURCE, csvPath);
            setValue(JRCsvQueryExecuterFactory.CSV_TIMEZONE, TimeZone.getTimeZone(zoneId));
            setValue(JRCsvQueryExecuterFactory.CSV_DATE_PATTERN, datePattern);
            setValue(JRCsvQueryExecuterFactory.CSV_NUMBER_FORMAT, numberFormat);
            setValue(JRCsvQueryExecuterFactory.CSV_NUMBER_PATTERN, numberPattern);
            setValue(JRCsvQueryExecuterFactory.CSV_INPUT_STREAM, new FileInputStream(csvPath));
            setValue(JRCsvQueryExecuterFactory.CSV_USE_FIRST_ROW_AS_HEADER, useFirstRowAsHeader);
            setValue(JRCsvQueryExecuterFactory.CSV_READER, new FileReader(csvPath));

        } catch (FileNotFoundException exception) {
            throw new ReportException("File not found", 404);
        }
    }

    public String getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
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
