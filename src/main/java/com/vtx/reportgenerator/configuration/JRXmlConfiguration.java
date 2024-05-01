package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.ReportException;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TimeZone;

public class JRXmlConfiguration extends AbstractJRFileConfiguration {
    private Document xmlDataDocument;
    private final String xmlPath;

    public JRXmlConfiguration(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    @Override
    public void afterPropertiesSet() {

        try {

            File file = new File(xmlPath);
            setValue(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, xmlDataDocument);
            setValue(JRXPathQueryExecuterFactory.XML_FILE, file);
            setValue(JRXPathQueryExecuterFactory.XML_INPUT_STREAM, new FileInputStream(file));
            setValue(JRXPathQueryExecuterFactory.XML_SOURCE, xmlPath);
            setValue(JRXPathQueryExecuterFactory.XML_TIME_ZONE, TimeZone.getTimeZone(zoneId));
            setValue(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, numberPattern);
            setValue(JRXPathQueryExecuterFactory.XML_LOCALE, locale);

        } catch (FileNotFoundException exception) {
            throw new ReportException("File not exist", 404);
        }
    }

    public void setXmlDataDocument(Document xmlDataDocument) {
        this.xmlDataDocument = xmlDataDocument;
    }
}
