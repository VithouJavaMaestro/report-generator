package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.utils.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TimeZone;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import org.w3c.dom.Document;

public class JRXmlConfiguration extends AbstractJRFileConfiguration {
    private Document xmlDataDocument;

    public JRXmlConfiguration(String xmlPath) {
        super(xmlPath);
        FileUtils.checkFileExtension(xmlPath, "xml");
    }

    @Override
    public void afterPropertiesSet() throws FileNotFoundException {

        File file = new File(path);
        setValue(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, xmlDataDocument);
        setValue(JRXPathQueryExecuterFactory.XML_FILE, file);
        setValue(JRXPathQueryExecuterFactory.XML_INPUT_STREAM, new FileInputStream(file));
        setValue(JRXPathQueryExecuterFactory.XML_SOURCE, path);
        setValue(JRXPathQueryExecuterFactory.XML_TIME_ZONE, TimeZone.getTimeZone(zoneId));
        setValue(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, numberPattern);
        setValue(JRXPathQueryExecuterFactory.XML_LOCALE, locale);

    }

    public void setXmlDataDocument(Document xmlDataDocument) {
        this.xmlDataDocument = xmlDataDocument;
    }
}
