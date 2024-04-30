package com.vtx.reportgenerator;

import com.vtx.reportgenerator.exporter.JRPdfReportExporter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JRPdfReportExporter.class)
public class ReportExporterConfiguration {
}
