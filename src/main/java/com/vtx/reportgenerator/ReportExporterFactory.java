package com.vtx.reportgenerator;

import com.vtx.reportgenerator.key.Key;
import java.util.List;

public class ReportExporterFactory {
    private final List<ReportExportationProvider> reportExportationProviders;

    public ReportExporterFactory(List<ReportExportationProvider> reportExportationProviders) {
        this.reportExportationProviders = reportExportationProviders;
    }

    public ReportExportationProvider determineExporter(Key key) {
        return reportExportationProviders
                .stream()
                .filter(configurationReportExportationProvider -> configurationReportExportationProvider.isMatchKey(key))
                .findFirst()
                .orElseThrow(() -> new ReportException("Configuration not support", 400));
    }
}


