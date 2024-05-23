package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.key.Key;
import java.io.OutputStream;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.HtmlExporterConfiguration;
import net.sf.jasperreports.export.HtmlExporterOutput;
import net.sf.jasperreports.export.HtmlReportConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

public class JRHtmlReportExporter implements JROutputManager {

    @Override
    public HtmlExporterOutput getExporterOutput(JRConfiguration jrConfiguration, OutputStream outputStream) {
        return new SimpleHtmlExporterOutput(outputStream);
    }

    @Override
    public ExporterInput getExporterInput(JRConfiguration jrConfiguration) {
        return jrConfiguration.getExporterInput();
    }

    @Override
    public HtmlReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    public HtmlExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    public ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    public Exporter<ExporterInput, HtmlReportConfiguration, HtmlExporterConfiguration, HtmlExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return new HtmlExporter();
    }

    @Override
    public boolean isMatchKey(Key key) {
        return ExporterKey.HTML == key;
    }
}
