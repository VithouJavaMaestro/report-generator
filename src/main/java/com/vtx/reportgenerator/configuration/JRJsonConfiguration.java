package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.key.JRFileConfigurationKey;
import com.vtx.reportgenerator.key.Key;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;

import java.io.InputStream;
import java.util.TimeZone;

public class JRJsonConfiguration extends AbstractJRFileConfiguration {

    public JRJsonConfiguration(InputStream inputStream) {
        super(inputStream);
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        super.afterPropertiesSet();

        setValue(JsonQueryExecuterFactory.JSON_LOCALE_CODE, localCode);
        setValue(JsonQueryExecuterFactory.JSON_DATE_PATTERN, datePattern);
        setValue(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN, numberPattern);
        setValue(JsonQueryExecuterFactory.JSON_LOCALE, locale);
        setValue(JsonQueryExecuterFactory.JSON_TIME_ZONE, TimeZone.getTimeZone(zoneId));
        setValue(JsonQueryExecuterFactory.JSON_INPUT_STREAM, importedFile);

    }

    @Override
    public void prepareJasperDesignAndQueryInternal(JasperDesign jasperDesign, JRDesignQuery jrDesignQuery) {

    }

    @Override
    public Key getKey() {
        return JRFileConfigurationKey.JSON;
    }
}
