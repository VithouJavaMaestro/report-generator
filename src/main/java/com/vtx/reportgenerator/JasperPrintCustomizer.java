package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface JasperPrintCustomizer {
    void customize(JasperPrint jasperPrint) throws JRException;
}
