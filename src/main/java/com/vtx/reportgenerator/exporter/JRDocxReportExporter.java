package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.AbstractJRExporter;
import com.vtx.reportgenerator.JRConfiguration;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.DocxExporterConfiguration;
import net.sf.jasperreports.export.DocxReportConfiguration;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.File;

public class JRDocxReportExporter extends AbstractJRExporter<ExporterInput, DocxReportConfiguration, DocxExporterConfiguration, OutputStreamExporterOutput> {
    public JRDocxReportExporter(String exportPath) {
        super(exportPath);
    }

    public JRDocxReportExporter(File file) {
        super(file);
    }

    @Override
    protected String[] getExtension() {
        return new String[]{"doc", "docx"};
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
    protected DocxReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected DocxExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    protected Exporter<ExporterInput, DocxReportConfiguration, DocxExporterConfiguration, OutputStreamExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return new JRDocxExporter();
    }
}
