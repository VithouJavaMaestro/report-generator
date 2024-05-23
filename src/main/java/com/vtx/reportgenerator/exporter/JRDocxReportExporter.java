package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.key.Key;
import java.io.OutputStream;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.DocxExporterConfiguration;
import net.sf.jasperreports.export.DocxReportConfiguration;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JRDocxReportExporter implements JROutputManager {

    @Override
    public OutputStreamExporterOutput getExporterOutput(JRConfiguration jrConfiguration, OutputStream outputStream) {
        return new SimpleOutputStreamExporterOutput(outputStream);
    }

    @Override
    public ExporterInput getExporterInput(JRConfiguration jrConfiguration) {
        return jrConfiguration.getExporterInput();
    }

    @Override
    public DocxReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    public DocxExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    public ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    public Exporter<ExporterInput, DocxReportConfiguration, DocxExporterConfiguration, OutputStreamExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return new JRDocxExporter();
    }

    @Override
    public boolean isMatchKey(Key key) {
        return ExporterKey.DOCX == key || ExporterKey.DOC == key;
    }
}
