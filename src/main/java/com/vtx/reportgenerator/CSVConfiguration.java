package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.query.JRCsvQueryExecuterFactory;
import net.sf.jasperreports.export.ExporterInput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.TimeZone;

public class CSVConfiguration extends AbstractConfiguration {
    private String csvPath;
    private String csvEncoding = "UTF-8";
    private String csvDelimiter;
    private boolean useFirstRowAsHeader;
    private boolean isIgnorePagination;

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

    @Override
    public ExporterInput getExporterInput() {

        try {

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

            return processJasperReportTemplates();

        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("File not found", exception);
        }
    }
}
