package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.AbstractJRExporter;
import com.vtx.reportgenerator.JRConfiguration;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.File;

public class JRPdfReportExporter extends AbstractJRExporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> {

    public JRPdfReportExporter(String exportPath) {
        super(exportPath);
    }

    public JRPdfReportExporter(File file) {
        super(file);
    }

    @Override
    protected OutputStreamExporterOutput getExporterOutput(JRConfiguration jrConfiguration) {
        return new SimpleOutputStreamExporterOutput(outputStream);
    }

    @Override
    protected ExporterInput getExporterInput(JRConfiguration jrConfiguration) {
        return jrConfiguration.getExporterInput();
    }

    @Override
    protected PdfReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected PdfExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    protected Exporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return new JRPdfExporter();
    }

    @Override
    protected String[] getExtension() {
        return new String[]{"pdf"};
    }
}
