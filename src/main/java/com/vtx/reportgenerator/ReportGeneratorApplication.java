package com.vtx.reportgenerator;

import com.vtx.reportgenerator.configuration.AbstractJRConfiguration;
import com.vtx.reportgenerator.configuration.JRCSVConfiguration;
import com.vtx.reportgenerator.configuration.JRJsonConfiguration;
import com.vtx.reportgenerator.configuration.JRXlsxConfiguration;
import com.vtx.reportgenerator.configuration.JRXmlConfiguration;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.key.JRFileConfigurationKey;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Locale;
import java.util.UUID;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class ReportGeneratorApplication {
    private final JRReportExporterFactory reportExporterFactory;

    public ReportGeneratorApplication(JRReportExporterFactory reportExporterFactory) {
        this.reportExporterFactory = reportExporterFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReportGeneratorApplication.class, args);
    }

    @GetMapping(value = "/file/report/download")
    public ResponseEntity<Resource> download(
            @RequestParam("path") String path
    ) throws IOException {

        String resourcePath = "C:\\Users\\THENCHANTHAVITHOU\\Documents\\my-project\\report-generator\\src\\main\\resources";
        Resource resource = new FileSystemResource(Path.of(resourcePath, path));

        ContentDisposition contentDisposition = ContentDisposition.attachment()
                .filename(resource.getFilename())
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(contentDisposition);
        httpHeaders.setContentLength(resource.contentLength());

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping(value = "/file/report/configuration")
    public ResponseEntity<Resource> configuration(
            @RequestParam("file") MultipartFile file,
            @RequestParam("templateFile") MultipartFile templateFile,
            @RequestParam("type") ExporterKey type,
            @RequestParam("node") String node) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {

        String resourcePath = "C:\\Users\\THENCHANTHAVITHOU\\Documents\\my-project\\report-generator\\src\\main\\resources";

        Path templatePath = Path.of(resourcePath, templateFile.getOriginalFilename());
        Path importPath = Path.of(resourcePath, file.getOriginalFilename());
        templateFile.transferTo(templatePath);
        file.transferTo(importPath);


        JRReportExportationProvider reportExportationProvider = reportExporterFactory.determineExporter(type);

        if (file.getOriginalFilename() != null) {
            JRConfiguration configuration = getConfiguration(node, file.getInputStream(), FilenameUtils.getExtension(file.getOriginalFilename()), templatePath.toString());
            byte[] bytes = reportExportationProvider.exportReport(configuration);
            ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
            ContentDisposition contentDisposition = ContentDisposition
                    .attachment()
                    .filename(UUID.randomUUID() + "." + type.getName())
                    .build();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentDisposition(contentDisposition);
            httpHeaders.setContentLength(byteArrayResource.contentLength());
            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(byteArrayResource);
        }

        return null;
    }

    private JRConfiguration getConfiguration(String node, InputStream inputStream, String type, String jrxmlPath) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {

        JRFileConfigurationKey configurationKey = JRFileConfigurationKey.valueOf(type.toUpperCase(Locale.ENGLISH));

        AbstractJRConfiguration configuration = determineJrConfiguration(node, inputStream, configurationKey);


        configuration.addJrXml(new FileInputStream(jrxmlPath));

        return configuration;
    }


    private static AbstractJRConfiguration determineJrConfiguration(String node, InputStream inputStream, JRFileConfigurationKey configurationKey) {

        AbstractJRConfiguration configuration;

        switch (configurationKey) {
            case CSV -> configuration = new JRCSVConfiguration(inputStream);
            case JSON -> configuration = new JRJsonConfiguration(inputStream);
            case XML -> configuration = new JRXmlConfiguration(inputStream, node);
            case XLSX -> configuration = new JRXlsxConfiguration(inputStream);
            default -> throw new ReportException("No such configuration key found", 404);
        }

        return configuration;
    }
}
