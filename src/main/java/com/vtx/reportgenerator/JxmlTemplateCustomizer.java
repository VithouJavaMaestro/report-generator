package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

import java.io.InputStream;

@FunctionalInterface
public interface JxmlTemplateCustomizer {
    JasperReport customize(InputStream jrxml) throws JRException;
}
