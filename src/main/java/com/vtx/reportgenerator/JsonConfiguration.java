package com.vtx.reportgenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TimeZone;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.export.ExporterInput;

public class JsonConfiguration extends AbstractFileReaderConfiguration {
    private String jsonPath;

    public JsonConfiguration(String jsonPath) {
        FileUtils.checkFileExtension(jsonPath, "json");
        this.jsonPath = jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        if (jsonPath == null) {
            throw new IllegalArgumentException("jsonPath cannot be null");
        }
        this.jsonPath = jsonPath;
    }

    @Override
    public ExporterInput getExporterInput() {

        try {

            setValue(JsonQueryExecuterFactory.JSON_LOCALE_CODE, localCode);
            setValue(JsonQueryExecuterFactory.JSON_DATE_PATTERN, datePattern);
            setValue(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN, numberPattern);
            setValue(JsonQueryExecuterFactory.JSON_LOCALE, locale);
            setValue(JsonQueryExecuterFactory.JSON_SOURCE, jsonPath);
            setValue(JsonQueryExecuterFactory.JSON_TIME_ZONE, TimeZone.getTimeZone(zoneId));
            setValue(JsonQueryExecuterFactory.JSON_INPUT_STREAM, new FileInputStream(jsonPath));

            return processJasperReportTemplates();

        } catch (FileNotFoundException exception) {
            if (logger.isDebugEnabled()) {
                logger.debug("File not found");
            }
            throw new ReportException("File not found", 404);
        }
    }
}
