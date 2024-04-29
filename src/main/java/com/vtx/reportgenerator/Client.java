package com.vtx.reportgenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;

public class Client {
    public static void main(String[] args) throws FileNotFoundException, JRException {
        var path = "C:\\Users\\Chanthavithou THEN\\Documents\\lib-commons\\report-generator\\src\\main\\resources\\employee-report.jrxml";
        var jsonPath = "C:\\Users\\Chanthavithou THEN\\Downloads\\RandomData (1).json";
        var outPath = "C:\\Users\\Chanthavithou THEN\\Documents\\lib-commons\\report-generator\\src\\main\\resources\\Sample2.pdf";
//        var imagePath = "C:\\Users\\THENCHANTHAVITHOU\\Pictures\\Screenshots\\Screenshot 2023-10-30 221133.png";
//        var front = "C:\\Users\\THENCHANTHAVITHOU\\Documents\\jasper\\Picture3.jpg";
//        var back = "C:\\Users\\THENCHANTHAVITHOU\\Documents\\jasper\\Picture2.jpg";

//        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage());
//
//        ImageIcon documentFront = new ImageIcon(new ImageIcon(front).getImage());
//        ImageIcon documentBack = new ImageIcon(new ImageIcon(back).getImage());
//
//        //json
//        JsonConfiguration jsonConfiguration = new JsonConfiguration(jsonPath);
//        jsonConfiguration.setJsonPath(jsonPath);
//        JRXMLTemplate jrxmlTemplate = new JRXMLTemplate();
//        jrxmlTemplate.setJrXml(new FileInputStream(path));
//        jrxmlTemplate.setWhenNoDataTypeEnum(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
//        jsonConfiguration.addJrXml(jrxmlTemplate);

//        jsonConfiguration.setValue("logo", imageIcon.getImage());
//        jsonConfiguration.setValue("documentFront", documentFront.getImage());
//        jsonConfiguration.setValue("documentBack", documentBack.getImage());

//
//        JRPdfReportExporter jrPdfReportExporter = new JRPdfReportExporter(outPath);
//        jrPdfReportExporter.exportReport(jsonConfiguration);

//        String csvPath = "C:\\Users\\THENCHANTHAVITHOU\\Downloads\\MOCK_DATA (2).csv";
//        CSVConfiguration csvConfiguration = new CSVConfiguration();
//        csvConfiguration.setCsvDelimiter(",");
//        csvConfiguration.setCsvEncoding(StandardCharsets.UTF_8.name());
//        csvConfiguration.setCsvPath(csvPath);
//        JRXMLTemplate jrxmlTemplate = new JRXMLTemplate();
//        jrxmlTemplate.setWhenNoDataTypeEnum(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
//        jrxmlTemplate.setJrXml(new FileInputStream(path));
//        csvConfiguration.addJrXml(jrxmlTemplate);
//
//
//        JRPdfReportExporter jrPdfReportExporter = new JRPdfReportExporter(outPath);
//        jrPdfReportExporter.exportReport(csvConfiguration);

//        String excelPath = "C:\\Users\\Chanthavithou THEN\\Downloads\\RandomData.xlsx";
//        ExcelConfiguration excelConfiguration = new ExcelConfiguration(excelPath, (jasperReport, jasperPrint) -> {
//            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
//            jasperReport.setSectionType(SectionTypeEnum.BAND);
//        });
//        excelConfiguration.setUserFirstRowAsHeader(false);
//        excelConfiguration.addJrXml(new FileInputStream(path));
//
//        JRPdfReportExporter jrPdfReportExporter = new JRPdfReportExporter(outPath);
//        jrPdfReportExporter.exportReport(excelConfiguration);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("John", "Doe", "Johndoe@gmail.com"));
        personList.add(new Person("Jane", "Jones", "Janes jones@gmail.com"));

        JRBeanDataSourceConfiguration<Person> javaBeanCollectionDataSource = new JRBeanDataSourceConfiguration<>(personList);
        javaBeanCollectionDataSource.addJrXml(new FileInputStream(path));
        javaBeanCollectionDataSource.setCustomizer((jasperReport, jasperPrint) -> jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL));

        JRPdfReportExporter jrBeanDataSourceConfigurationReportExporter = new JRPdfReportExporter(outPath);
        jrBeanDataSourceConfigurationReportExporter.exportReport(javaBeanCollectionDataSource);
    }
}
