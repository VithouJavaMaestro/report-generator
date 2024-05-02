package com.vtx.reportgenerator;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.vtx.reportgenerator.configuration.Configuration;
import net.sf.jasperreports.export.ExporterInput;

public interface JRConfiguration extends Configuration {

    String DEFAULT_TIME_ZONE = "UTC";
    Locale DEFAULT_LOCALE = Locale.ENGLISH;
    String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    String DEFAULT_NUMBER_PATTERN = "#,##0.##";
    NumberFormat DEFAULT_NUMBER_FORMAT = new DecimalFormat();
    DateFormat DEFAULT_DATE_FORMAT = new StdDateFormat();
    String DEFAULT_LOCALE_CODE = "en_US";

    ExporterInput getExporterInput();

}
