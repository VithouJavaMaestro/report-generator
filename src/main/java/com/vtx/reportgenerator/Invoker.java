package com.vtx.reportgenerator;

import com.vtx.reportgenerator.configuration.JRJsonConfiguration;
import com.vtx.reportgenerator.exporter.JRXlsReportExporter;
import com.vtx.reportgenerator.exporter.JRXlsxReportExporter;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Invoker {
    public static void main(String[] args) throws IOException {
        var jsonPath = "C:\\Users\\THENCHANTHAVITHOU\\Downloads\\MOCK_DATA (2).json";
        var jrxmlPath = "C:\\Users\\THENCHANTHAVITHOU\\Documents\\my-project\\report-generator\\src\\main\\resources\\employee-report.jrxml";
        JRJsonConfiguration jsonConfiguration = new JRJsonConfiguration(new FileInputStream(jsonPath));
        jsonConfiguration.addJrXml(new FileInputStream(jrxmlPath));
//        jsonConfiguration.setJxmlTemplateCustomizer((jrxml) -> {
//            JasperDesign jasperDesign = JRXmlLoader.load(jrxml);
//            JRDesignQuery jrDesignQuery = new JRDesignQuery();
//            jrDesignQuery.setLanguage(JRFileConfigurationKey.JSON.getName());
//            jasperDesign.setQuery(jrDesignQuery);
//            return JasperCompileManager.compileReport(jasperDesign);
//        });
        JRXlsxReportExporter jrDocxReportExporter = new JRXlsxReportExporter();
        new ByteArrayInputStream(jrDocxReportExporter.exportReport(jsonConfiguration))
                .transferTo(new FileOutputStream("C:\\Users\\THENCHANTHAVITHOU\\Documents\\my-project\\report-generator\\src\\main\\resources\\test.xlsx"));
    }
}
