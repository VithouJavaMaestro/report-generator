package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.AbstractJRExporter;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.key.Key;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.HtmlExporterConfiguration;
import net.sf.jasperreports.export.HtmlExporterOutput;
import net.sf.jasperreports.export.HtmlReportConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

public class JRHtmlReportExporter extends AbstractJRExporter<ExporterInput, HtmlReportConfiguration, HtmlExporterConfiguration, HtmlExporterOutput> {

    @Override
    protected HtmlExporterOutput getExporterOutput(JRConfiguration jrConfiguration) {
        return new SimpleHtmlExporterOutput(byteArrayOutputStream);
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
    public boolean isMatchKey(Key key) {
        return ExporterKey.HTML == key;
    }
}
