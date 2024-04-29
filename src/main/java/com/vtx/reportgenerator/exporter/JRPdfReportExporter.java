package com.vtx.reportgenerator;

import java.io.File;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JRPdfReportExporter extends AbstractJRExporter {

    public JRPdfReportExporter(String exportPath) {
        super(exportPath);
    }

    public JRPdfReportExporter(File file) {
        super(file);
    }

    @Override
    public void exportReport(JRConfiguration configuration) {
        try {
            JRPdfExporter jrPdfExporter = new JRPdfExporter();
            jrPdfExporter.setExporterInput(configuration.getExporterInput());
            jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            jrPdfExporter.exportReport();
        } catch (JRException exception) {
            logger.error("An error occurred while export report with pdf", exception);
            throw new ReportException("An error occurred while export report with pdf", exception, 500);
        }
    }

    @Override
    protected String getExtension() {
        return "pdf";
    }
}
