package com.vtx.reportgenerator;

import com.vtx.reportgenerator.key.Key;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExporterReportProviderCache {

    private static final Logger log = LoggerFactory.getLogger(ExporterReportProviderCache.class);

    private final Map<Key, ReportExportationProvider> exportationCaches;

    public ExporterReportProviderCache() {
        exportationCaches = new HashMap<>();
    }

    public ReportExportationProvider getExportation(Key exportationType) {
        if (exportationCaches.containsKey(exportationType)) {
            final var exportationCache = exportationCaches.get(exportationType);
            final var exportationId = exportationCache.getId();
            final var exportationName = exportationCache.getProvider().getName();
            log.info("<<<(cache being called) with name: {} and id: {}", exportationName, exportationId);
            return exportationCache;
        }
        return null;
    }

    public void addReportExportation(ReportExportationProvider reportExportation) {
        this.exportationCaches.put(reportExportation.getProvider(), reportExportation);
    }

}
