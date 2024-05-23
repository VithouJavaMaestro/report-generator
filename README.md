# JasperReports Export Tool

The JasperReports Export Tool is a Java utility for generating reports from JasperReports and exporting them to different file formats including PDF, DOCX, DOC, PPT, PPTX, and XLSX.

## Features

- Generates reports from JasperReports templates.
- Exports reports to various file formats including PDF, DOCX, DOC, PPT, PPTX, and XLSX.
- Supports customization of report templates and output formats.
- Simple and easy-to-use API for integration into Java applications.
- Imports data from various sources including:
  - JSON: Import data from JSON format.
  - XML: Import data from XML format.
  - Excel: Import data from Excel format.
  - CSV: Import data from CSV format.
  - JDBC: Import data directly from a JDBC data source.
  - Collection Bean Data: Import data from a collection bean.
    
## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- Apache Maven for building the project.

### Installation

- Clone the repository

### Usage

```java
import com.example.jasperreports.JasperReportsExporter;

public class ReportGenerator {
    public static void main(String[] args) {

        ReportExportationProvider reportExportationProvider = exporterReportProviderLocator.getExportationProvider(ProviderKey.JASPER_REPORT);

        String jsonPath = "";

        String jrxmlTemplate = "";

        JRJsonConfiguration jrJsonConfiguration = new JRJsonConfiguration(new FileInputStream(jsonPath));
        jrJsonConfiguration.setExporterKey(ExporterKey.PDF);
        jrJsonConfiguration.setJrXmlTemplate(new FileInputStream(jrxmlTemplate));

        String outputPath = "";
        byte[] response = reportExportationProvider.exportReport(jrJsonConfiguration);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath)) {
            fileOutputStream.write(response);
        }

    }
}
