package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

import java.io.InputStream;

@FunctionalInterface
public interface JrxmlTemplateCustomizer {
    JasperReport customize(InputStream jrxml) throws JRException;
}
