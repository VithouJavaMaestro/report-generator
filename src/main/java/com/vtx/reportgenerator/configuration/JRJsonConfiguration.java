package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.utils.FileUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TimeZone;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;

public class JRJsonConfiguration extends AbstractJRFileConfiguration {

    public JRJsonConfiguration(String jsonPath) {
        super(jsonPath);
        FileUtils.checkFileExtension(jsonPath, "json");
    }


    @Override
    public void afterPropertiesSet() throws FileNotFoundException {

        setValue(JsonQueryExecuterFactory.JSON_LOCALE_CODE, localCode);
        setValue(JsonQueryExecuterFactory.JSON_DATE_PATTERN, datePattern);
        setValue(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN, numberPattern);
        setValue(JsonQueryExecuterFactory.JSON_LOCALE, locale);
        setValue(JsonQueryExecuterFactory.JSON_SOURCE, path);
        setValue(JsonQueryExecuterFactory.JSON_TIME_ZONE, TimeZone.getTimeZone(zoneId));
        setValue(JsonQueryExecuterFactory.JSON_INPUT_STREAM, new FileInputStream(path));

    }
}
