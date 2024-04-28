package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.export.JRPdfExporter;

public enum ExporterType {
    JRPDF(JRPdfExporter.class.getName());

    private final String value;

    ExporterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
