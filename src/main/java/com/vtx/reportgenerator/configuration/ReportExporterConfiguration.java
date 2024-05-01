package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.ReportExporterFactory;
import com.vtx.reportgenerator.exporter.JRDocxReportExporter;
import com.vtx.reportgenerator.exporter.JRHtmlReportExporter;
import com.vtx.reportgenerator.exporter.JRPdfReportExporter;
import com.vtx.reportgenerator.exporter.JRPptxReportExporter;
import com.vtx.reportgenerator.exporter.JRXlsxReportExporter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ReportExporterFactory.class, JRDocxReportExporter.class, JRHtmlReportExporter.class, JRPdfReportExporter.class, JRPptxReportExporter.class, JRXlsxReportExporter.class})
public class ReportExporterConfiguration {
}
