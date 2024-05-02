package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.ReportException;
import com.vtx.reportgenerator.key.JRFileConfigurationKey;
import com.vtx.reportgenerator.key.Key;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import java.io.InputStream;
import java.util.TimeZone;

public class JRXmlConfiguration extends AbstractJRFileConfiguration {
    private Document xmlDataDocument;
    private final String xPathNode;

    public JRXmlConfiguration(InputStream inputStream, String xPathNode) {
        super(inputStream);
        if (StringUtils.isEmpty(xPathNode)) {
            throw new ReportException("xPath node cannot be null", 400);
        }
        this.xPathNode = xPathNode;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        super.afterPropertiesSet();

        setValue(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, xmlDataDocument);
        setValue(JRXPathQueryExecuterFactory.XML_INPUT_STREAM, importedFile);
        setValue(JRXPathQueryExecuterFactory.XML_TIME_ZONE, TimeZone.getTimeZone(zoneId));
        setValue(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, numberPattern);
        setValue(JRXPathQueryExecuterFactory.XML_LOCALE, locale);

    }

    @Override
    public void prepareJasperDesignAndQueryInternal(JasperDesign jasperDesign, JRDesignQuery jrDesignQuery) {
        jrDesignQuery.setText(xPathNode);
    }

    @Override
    public Key getKey() {
        return JRFileConfigurationKey.XML;
    }

    public void setXmlDataDocument(Document xmlDataDocument) {
        this.xmlDataDocument = xmlDataDocument;
    }
}
