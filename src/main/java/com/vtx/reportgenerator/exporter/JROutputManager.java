package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.JRConfiguration;
import java.io.OutputStream;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;

public interface JROutputManager extends FileOutputManager {

    ExporterOutput getExporterOutput(JRConfiguration jrConfiguration, OutputStream outputStream);

    ExporterInput getExporterInput(JRConfiguration jrConfiguration);

    ReportExportConfiguration getReportConfiguration(JRConfiguration jrConfiguration);

    ExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration);

    ReportContext getReportContext(JRConfiguration configuration);

    @SuppressWarnings("rawtypes")
    Exporter getExporter(JRConfiguration jrConfiguration);

}
