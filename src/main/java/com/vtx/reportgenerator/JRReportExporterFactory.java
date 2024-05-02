package com.vtx.reportgenerator;

import com.vtx.reportgenerator.key.Key;

import java.util.List;

public class JRReportExporterFactory {
    private final List<JRReportExportationProvider> reportExportationProviders;

    public JRReportExporterFactory(List<JRReportExportationProvider> reportExportationProviders) {
        this.reportExportationProviders = reportExportationProviders;
    }

    public JRReportExportationProvider determineExporter(Key key) {
        return reportExportationProviders
                .stream()
                .filter(configurationReportExportationProvider -> configurationReportExportationProvider.isMatchKey(key))
                .findFirst()
                .orElseThrow(() -> new ReportException("Configuration not support", 400));
    }
}


