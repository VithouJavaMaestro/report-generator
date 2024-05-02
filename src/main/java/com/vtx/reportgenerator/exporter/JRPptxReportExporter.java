package com.vtx.reportgenerator.exporter;

import com.vtx.reportgenerator.AbstractJRExporter;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.JRConfiguration;
import com.vtx.reportgenerator.key.Key;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.PptxExporterConfiguration;
import net.sf.jasperreports.export.PptxReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JRPptxReportExporter extends AbstractJRExporter<ExporterInput, PptxReportConfiguration, PptxExporterConfiguration, OutputStreamExporterOutput> {
    @Override
    protected OutputStreamExporterOutput getExporterOutput(JRConfiguration jrConfiguration) {
        return new SimpleOutputStreamExporterOutput(byteArrayOutputStream);
    }

    @Override
    protected ExporterInput getExporterInput(JRConfiguration jrConfiguration) {
        return jrConfiguration.getExporterInput();
    }

    @Override
    protected PptxReportConfiguration getReportConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected PptxExporterConfiguration getExporterConfiguration(JRConfiguration jrConfiguration) {
        return null;
    }

    @Override
    protected ReportContext getReportContext(JRConfiguration configuration) {
        return null;
    }

    @Override
    protected Exporter<ExporterInput, PptxReportConfiguration, PptxExporterConfiguration, OutputStreamExporterOutput> getExporter(JRConfiguration jrConfiguration) {
        return new JRPptxExporter();
    }

    @Override
    public boolean isMatchKey(Key key) {
        return ExporterKey.PPTX == key || ExporterKey.PPT == key;
    }
}
