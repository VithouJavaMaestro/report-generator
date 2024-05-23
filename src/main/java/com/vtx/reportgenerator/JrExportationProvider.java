package com.vtx.reportgenerator;

import com.vtx.reportgenerator.configuration.Configuration;
import com.vtx.reportgenerator.exporter.JROutputManager;
import com.vtx.reportgenerator.key.Key;
import com.vtx.reportgenerator.key.ProviderKey;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@SuppressWarnings({"rawtypes", "unchecked"})
@Component
public class JrExportationProvider implements ReportExportationProvider {

    protected final Log logger = LogFactory.getLog(JrExportationProvider.class);

    protected ByteArrayOutputStream byteArrayOutputStream;
    private final List<JROutputManager> outputManagers;

    public JrExportationProvider(List<JROutputManager> outputManagers) {
        this.outputManagers = outputManagers;
    }

    @Override
    public byte[] exportReport(Configuration configuration) throws ReportException {

        if (!(configuration instanceof JRConfiguration jrConfiguration)) {
            throw new ReportException("there is no such exporter", 400);
        }

        try {

            Optional<JROutputManager> optionalFileOutputManager = outputManagers.stream()
                    .filter(fileOutputManager -> fileOutputManager.isMatchKey(jrConfiguration.getExportationKey()))
                    .findFirst();

            if (optionalFileOutputManager.isPresent()) {

                this.byteArrayOutputStream = new ByteArrayOutputStream();

                JROutputManager jrOutputManager = optionalFileOutputManager.get();
                Exporter exporter = jrOutputManager.getExporter(jrConfiguration);

                ExporterInput exporterInput = jrOutputManager.getExporterInput(jrConfiguration);
                ExporterOutput exporterOutput = jrOutputManager.getExporterOutput(jrConfiguration, byteArrayOutputStream);
                ReportContext reportContext = jrOutputManager.getReportContext(jrConfiguration);
                ReportExportConfiguration reportExportConfiguration = jrOutputManager.getReportConfiguration(jrConfiguration);
                ExporterConfiguration exporterConfiguration = jrOutputManager.getExporterConfiguration(jrConfiguration);

                exporter.setExporterInput(exporterInput);
                exporter.setConfiguration(exporterConfiguration);
                exporter.setConfiguration(reportExportConfiguration);
                exporter.setExporterOutput(exporterOutput);
                exporter.setReportContext(reportContext);
                exporter.exportReport();

                return byteArrayOutputStream.toByteArray();

            }

            return new byte[]{};
        } catch (JRException exception) {
            String msg = "An error occurred during export report";
            if (logger.isErrorEnabled()) {
                logger.error(msg, exception);
            }
            throw new ReportException(msg, exception, 500);
        }
    }


    @Override
    public Key getProvider() {
        return ProviderKey.JASPER_REPORT;
    }

    @Override
    public String getId() {
        return "jasperReport";
    }
}
