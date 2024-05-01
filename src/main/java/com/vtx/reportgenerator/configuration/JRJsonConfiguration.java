package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.FileUtils;
import com.vtx.reportgenerator.ReportException;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TimeZone;

public class JRJsonConfiguration extends AbstractJRFileConfiguration {
    private String jsonPath;

    public JRJsonConfiguration(String jsonPath) {
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
    public void afterPropertiesSet() {

        try {

            setValue(JsonQueryExecuterFactory.JSON_LOCALE_CODE, localCode);
            setValue(JsonQueryExecuterFactory.JSON_DATE_PATTERN, datePattern);
            setValue(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN, numberPattern);
            setValue(JsonQueryExecuterFactory.JSON_LOCALE, locale);
            setValue(JsonQueryExecuterFactory.JSON_SOURCE, jsonPath);
            setValue(JsonQueryExecuterFactory.JSON_TIME_ZONE, TimeZone.getTimeZone(zoneId));
            setValue(JsonQueryExecuterFactory.JSON_INPUT_STREAM, new FileInputStream(jsonPath));

        } catch (FileNotFoundException exception) {
            if (logger.isDebugEnabled()) {
                logger.debug("File not found");
            }
            throw new ReportException("File not found", 404);
        }
    }
}
