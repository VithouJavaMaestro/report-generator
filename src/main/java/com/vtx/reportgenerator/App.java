package com.vtx.reportgenerator;

import com.vtx.reportgenerator.configuration.JRJsonConfiguration;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.key.ProviderKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class App implements InitializingBean {
    private final ExporterReportProviderLocator exporterReportProviderLocator;

    public App(ExporterReportProviderLocator exporterReportProviderLocator) {
        this.exporterReportProviderLocator = exporterReportProviderLocator;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ReportExportationProvider reportExportationProvider = exporterReportProviderLocator.getExportationProvider(ProviderKey.JASPER_REPORT);

        String jsonPath = "";

        String jrxmlTemplate = "";

        JRJsonConfiguration jrJsonConfiguration = new JRJsonConfiguration(new FileInputStream(jsonPath));
        jrJsonConfiguration.setExporterKey(ExporterKey.PDF);
        jrJsonConfiguration.setJrXmlTemplate(new FileInputStream(jrxmlTemplate));

        String outputPath = "";
        byte[] response = reportExportationProvider.exportReport(jrJsonConfiguration);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath)) {
            fileOutputStream.write(response);
        }
    }
}
