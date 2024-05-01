package com.vtx.reportgenerator;

import com.vtx.reportgenerator.configuration.Configuration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayOutputStream;

public abstract class AbstractJRExporter<I extends ExporterInput, IC extends ReportExportConfiguration,
        C extends ExporterConfiguration, O extends ExporterOutput> implements ReportExportationProvider {
    protected ByteArrayOutputStream byteArrayOutputStream;
    protected final Log logger = LogFactory.getLog(AbstractJRExporter.class);

    protected abstract O getExporterOutput(JRConfiguration jrConfiguration);

    protected abstract I getExporterInput(JRConfiguration jrConfiguration);

    protected abstract IC getReportConfiguration(JRConfiguration jrConfiguration);

    protected abstract C getExporterConfiguration(JRConfiguration jrConfiguration);

    protected abstract ReportContext getReportContext(JRConfiguration configuration);

    protected abstract Exporter<I, IC, C, O> getExporter(JRConfiguration jrConfiguration);

    @Override
    public byte[] exportReport(Configuration configuration) throws ReportException {

        if (!(configuration instanceof JRConfiguration jrConfiguration)) {
            throw new ReportException("No such configuration", 404);
        }

        this.byteArrayOutputStream = new ByteArrayOutputStream();

        try {

            Exporter<I, IC, C, O> exporter = getExporter(jrConfiguration);

            if (exporter != null) {
                exporter.setExporterOutput(getExporterOutput(jrConfiguration));
                exporter.setExporterInput(getExporterInput(jrConfiguration));
                exporter.setReportContext(getReportContext(jrConfiguration));
                exporter.setConfiguration(getReportConfiguration(jrConfiguration));
                exporter.setConfiguration(getExporterConfiguration(jrConfiguration));
                exporter.exportReport();
            }

            return byteArrayOutputStream.toByteArray();

        } catch (JRException exception) {
            String msg = "An error occurred during export report";
            if (logger.isErrorEnabled()) {
                logger.error(msg, exception);
            }
            throw new ReportException(msg, exception, 500);
        }
    }
}
