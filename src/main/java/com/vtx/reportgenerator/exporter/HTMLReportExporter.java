package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.AbstractJRExporter;
import com.vtx.reportgenerator.JRConfiguration;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.HtmlExporterConfiguration;
import net.sf.jasperreports.export.HtmlExporterOutput;
import net.sf.jasperreports.export.HtmlReportConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

import java.io.File;

public class HTMLReportExporter extends AbstractJRExporter<ExporterInput, HtmlReportConfiguration, HtmlExporterConfiguration, HtmlExporterOutput> {
    public HTMLReportExporter(String exportPath) {
        super(exportPath);
    }

    public HTMLReportExporter(File file) {
        super(file);
    }

    @Override
    protected HtmlExporterOutput getExporterOutput(JRConfiguration jrConfiguration) {
        return new SimpleHtmlExporterOutput(outputStream);
    }

    @Override
    protected ExporterInput getExporterInput(JRConfiguration jrConfiguration) {
        return jrConfiguration.getExporterInput();
    }

    @Override
    protected HtmlReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected HtmlExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    protected Exporter<ExporterInput, HtmlReportConfiguration, HtmlExporterConfiguration, HtmlExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return new HtmlExporter();
    }

    @Override
    protected String[] getExtension() {
        return new String[]{"html"};
    }
}
