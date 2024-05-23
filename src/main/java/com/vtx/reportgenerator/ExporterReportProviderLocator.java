package com.vtx.reportgenerator;

import com.vtx.reportgenerator.key.Key;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExporterReportProviderLocator {

    private static final ExporterReportProviderCache reportCaches = new ExporterReportProviderCache();
    private final List<ReportExportationProvider> reportExportation;

    public ExporterReportProviderLocator(List<ReportExportationProvider> reportExportation) {
        this.reportExportation = reportExportation;
        initExporter();
    }

    public void initExporter() {
        for (ReportExportationProvider exportation : reportExportation) {
            reportCaches.addReportExportation(exportation);
        }
    }

    public ReportExportationProvider getExportationProvider(Key key) {
        final var provider = reportCaches.getExportation(key);
        if (provider != null) {
            return provider;
        }
        throw new ReportException("Unable to get provider", 404);
    }
}
