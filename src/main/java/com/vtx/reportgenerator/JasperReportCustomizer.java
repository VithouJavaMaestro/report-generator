package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@FunctionalInterface
public interface JasperReportCustomizer {
    void customize(JasperPrint jasperPrint) throws JRException;
}
