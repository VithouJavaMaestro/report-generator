package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@FunctionalInterface
public interface JasperReportCustomizer {
    void customize(JasperReport jasperReport, JasperPrint jasperPrint);
}
