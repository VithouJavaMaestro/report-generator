package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public abstract class AbstractJRExporter<I extends ExporterInput, IC extends ReportExportConfiguration, C extends ExporterConfiguration, O extends ExporterOutput> implements ReportExporter<JRConfiguration> {
    protected BufferedOutputStream outputStream;
    protected final Log logger = LogFactory.getLog(AbstractJRExporter.class);

    protected AbstractJRExporter(String exportPath) {
        this(new File(exportPath));
    }

    protected AbstractJRExporter(File file) {
        FileUtils.checkFileExtension(file.getName(), getExtension());
        try {
            this.outputStream = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException exception) {
            if (logger.isDebugEnabled()) {
                logger.debug("export path not found");
            }
            throw new ReportException("export path not found", 404);
        }
    }

    @Override
    public void exportReport(JRConfiguration configuration) throws ReportException {
        try {
            Exporter<I, IC, C, O> exporter = getExporter(configuration);
            if (exporter != null) {
                exporter.setExporterOutput(getExporterOutput(configuration));
                exporter.setExporterInput(getExporterInput(configuration));
                exporter.setReportContext(getReportContext(configuration));
                exporter.setConfiguration(getReportConfiguration(configuration));
                exporter.setConfiguration(getExporterConfiguration(configuration));
                exporter.exportReport();
            }
        } catch (JRException exception) {
            String msg = "An error occurred during export report";
            if (logger.isErrorEnabled()) {
                logger.error(msg, exception);
            }
            throw new ReportException(msg, exception, 500);
        }
    }


    protected abstract O getExporterOutput(JRConfiguration jrConfiguration);

    protected abstract I getExporterInput(JRConfiguration jrConfiguration);

    protected abstract IC getReportConfiguration(JRConfiguration jrConfiguration);

    protected abstract C getExporterConfiguration(JRConfiguration jrConfiguration);

    protected abstract ReportContext getReportContext(JRConfiguration configuration);

    protected abstract Exporter<I, IC, C, O> getExporter(JRConfiguration jrConfiguration);

    protected abstract String[] getExtension();
}
