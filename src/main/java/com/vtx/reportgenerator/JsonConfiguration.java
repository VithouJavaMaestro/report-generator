package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.export.ExporterInput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.TimeZone;

public class JsonConfiguration extends AbstractConfiguration {
    private Path jsonPath;

    public void setJsonPath(Path jsonPath) {
        if (jsonPath == null) {
            throw new IllegalArgumentException("jsonPath cannot be null");
        }
        this.jsonPath = jsonPath;
    }

    @Override
    public ExporterInput getExporterInput() {

        try {

            setValue(JsonQueryExecuterFactory.JSON_DATE_PATTERN, datePattern);
            setValue(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN, numberPattern);
            setValue(JsonQueryExecuterFactory.JSON_LOCALE, locale);
            setValue(JsonQueryExecuterFactory.JSON_SOURCE, jsonPath.toString());
            setValue(JsonQueryExecuterFactory.JSON_TIME_ZONE, TimeZone.getTimeZone(zoneId));
            setValue(JsonQueryExecuterFactory.JSON_INPUT_STREAM, new FileInputStream(jsonPath.toString()));

            return processJasperReportTemplates();

        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("File cannot be found", exception);
        }
    }
}
