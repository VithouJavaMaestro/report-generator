package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.key.Key;
import java.io.OutputStream;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JRPdfReportExporter implements JROutputManager {

    @Override
    public OutputStreamExporterOutput getExporterOutput(JRConfiguration jrConfiguration, OutputStream outputStream) {
        return new SimpleOutputStreamExporterOutput(outputStream);
    }

    @Override
    public ExporterInput getExporterInput(JRConfiguration jrConfiguration) {
        return jrConfiguration.getExporterInput();
    }

    @Override
    public PdfReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    public PdfExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    public ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    public Exporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return new JRPdfExporter();
    }

    @Override
    public boolean isMatchKey(Key key) {
        return ExporterKey.PDF == key;
    }
}
