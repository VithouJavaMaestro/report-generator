package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.AbstractJRExporter;
import com.vtx.reportgenerator.JRConfiguration;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.XlsExporterConfiguration;
import net.sf.jasperreports.export.XlsReportConfiguration;

import java.io.File;

public class XlsReportExporter extends AbstractJRExporter<ExporterInput, XlsReportConfiguration, XlsExporterConfiguration, OutputStreamExporterOutput> {
    public XlsReportExporter(String exportPath) {
        super(exportPath);
    }

    public XlsReportExporter(File file) {
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
    protected XlsReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected XlsExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    protected Exporter<ExporterInput, XlsReportConfiguration, XlsExporterConfiguration, OutputStreamExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected String[] getExtension() {
        return new String[]{"xls", "xlsx"};
    }
}
