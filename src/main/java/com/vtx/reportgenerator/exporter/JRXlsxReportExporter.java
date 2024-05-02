package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.AbstractJRExporter;
import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.key.Key;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.XlsxExporterConfiguration;
import net.sf.jasperreports.export.XlsxReportConfiguration;

public class JRXlsxReportExporter extends AbstractJRExporter<ExporterInput, XlsxReportConfiguration, XlsxExporterConfiguration, OutputStreamExporterOutput> {
    @Override
    protected OutputStreamExporterOutput getExporterOutput(JRConfiguration jrConfiguration) {
        return new SimpleOutputStreamExporterOutput(byteArrayOutputStream);
    }

    @Override
    protected ExporterInput getExporterInput(JRConfiguration jrConfiguration) {
        return jrConfiguration.getExporterInput();
    }

    @Override
    protected XlsxReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected XlsxExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    protected Exporter<ExporterInput, XlsxReportConfiguration, XlsxExporterConfiguration, OutputStreamExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return new JRXlsxExporter();
    }

    @Override
    public boolean isMatchKey(Key key) {
        return ExporterKey.XLSX == key;
    }
}
