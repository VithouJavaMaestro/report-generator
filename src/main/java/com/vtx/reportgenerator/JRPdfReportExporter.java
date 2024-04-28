package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

public class JRPdfReportExporter extends AbstractJRExporter {
    private final Log logger = LogFactory.getLog(JRPdfReportExporter.class);

    public JRPdfReportExporter(OutputStream output) {
        super(output);
    }

    public JRPdfReportExporter(String exportPath) throws FileNotFoundException {
        super(exportPath);
    }

    public JRPdfReportExporter(File file) throws FileNotFoundException {
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
            throw new ReportException("An error occurred while export report with pdf", exception);
        }
    }
}
