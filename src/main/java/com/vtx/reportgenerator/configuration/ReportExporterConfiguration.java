package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.exporter.JRDocxReportExporter;
import com.vtx.reportgenerator.exporter.JRHtmlReportExporter;
import com.vtx.reportgenerator.exporter.JRPdfReportExporter;
import com.vtx.reportgenerator.exporter.JRPptxReportExporter;
import com.vtx.reportgenerator.exporter.JRXlsReportExporter;
import com.vtx.reportgenerator.exporter.JRXlsxReportExporter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JRXlsxReportExporter.class, JRDocxReportExporter.class, JRHtmlReportExporter.class, JRPdfReportExporter.class, JRPptxReportExporter.class, JRXlsReportExporter.class})
public class ReportExporterConfiguration {
}
