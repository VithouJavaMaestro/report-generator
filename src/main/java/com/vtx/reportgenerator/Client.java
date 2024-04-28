package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        var path = "C:\\Users\\THENCHANTHAVITHOU\\Documents\\my-project\\report-generator\\src\\main\\resources\\json-learning-001.jrxml";
        var jsonPath = "C:\\Users\\THENCHANTHAVITHOU\\Downloads\\MOCK_DATA (1).json";
        var outPath = "C:\\Users\\THENCHANTHAVITHOU\\Documents\\my-project\\report-generator\\src\\main\\resources\\Sample1.pdf";
        var imagePath = "C:\\Users\\THENCHANTHAVITHOU\\Pictures\\Screenshots\\Screenshot 2023-10-30 221133.png";
        var front = "C:\\Users\\THENCHANTHAVITHOU\\Documents\\jasper\\Picture3.jpg";
        var back = "C:\\Users\\THENCHANTHAVITHOU\\Documents\\jasper\\Picture2.jpg";

//        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage());
//
//        ImageIcon documentFront = new ImageIcon(new ImageIcon(front).getImage());
//        ImageIcon documentBack = new ImageIcon(new ImageIcon(back).getImage());
//
//        //json
//        JsonConfiguration jsonConfiguration = new JsonConfiguration();
//        jsonConfiguration.setJsonPath(Path.of(jsonPath));
//        jsonConfiguration.addJrXml(new FileInputStream(path));
//
//        jsonConfiguration.setValue("logo", imageIcon.getImage());
//        jsonConfiguration.setValue("documentFront", documentFront.getImage());
//        jsonConfiguration.setValue("documentBack", documentBack.getImage());
//
//
//        JRPdfReportExporter jrPdfReportExporter = new JRPdfReportExporter(outPath);
//        jrPdfReportExporter.exportReport(jsonConfiguration);

        String csvPath = "C:\\Users\\THENCHANTHAVITHOU\\Downloads\\MOCK_DATA (2).csv";
        CSVConfiguration csvConfiguration = new CSVConfiguration();
        csvConfiguration.setCsvDelimiter(",");
        csvConfiguration.setCsvEncoding(StandardCharsets.UTF_8.name());
        csvConfiguration.setCsvPath(csvPath);
        JRXMLTemplate jrxmlTemplate = new JRXMLTemplate();
        jrxmlTemplate.setWhenNoDataTypeEnum(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
        jrxmlTemplate.setJrXml(new FileInputStream(path));
        csvConfiguration.addJrXml(jrxmlTemplate);


        JRPdfReportExporter jrPdfReportExporter = new JRPdfReportExporter(outPath);
        jrPdfReportExporter.exportReport(csvConfiguration);
    }
}
