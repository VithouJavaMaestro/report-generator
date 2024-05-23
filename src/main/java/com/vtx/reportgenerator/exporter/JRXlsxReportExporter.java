package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.key.Key;
import java.io.OutputStream;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.XlsxExporterConfiguration;
import net.sf.jasperreports.export.XlsxReportConfiguration;

public class JRXlsxReportExporter implements JROutputManager {
    @Override
    public OutputStreamExporterOutput getExporterOutput(JRConfiguration jrConfiguration, OutputStream outputStream) {
        return new SimpleOutputStreamExporterOutput(outputStream);
    }

    @Override
    public ExporterInput getExporterInput(JRConfiguration jrConfiguration) {
        return jrConfiguration.getExporterInput();
    }

    @Override
    public XlsxReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    public XlsxExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    public ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    public Exporter<ExporterInput, XlsxReportConfiguration, XlsxExporterConfiguration, OutputStreamExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return new JRXlsxExporter();
    }

    @Override
    public boolean isMatchKey(Key key) {
        return ExporterKey.XLSX == key;
    }
}
