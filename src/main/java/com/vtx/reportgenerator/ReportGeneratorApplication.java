package com.vtx.reportgenerator;

import com.vtx.reportgenerator.configuration.AbstractJRConfiguration;
import com.vtx.reportgenerator.configuration.Configuration;
import com.vtx.reportgenerator.configuration.JRCSVConfiguration;
import com.vtx.reportgenerator.configuration.JRJsonConfiguration;
import com.vtx.reportgenerator.configuration.JRXlsxConfiguration;
import com.vtx.reportgenerator.configuration.JRXmlConfiguration;
import com.vtx.reportgenerator.key.ExporterKey;
import com.vtx.reportgenerator.key.JRFileConfigurationKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.UUID;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class ReportGeneratorApplication {
    private final ReportExporterFactory reportExporterFactory;

    public ReportGeneratorApplication(ReportExporterFactory reportExporterFactory) {
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
            @RequestParam("type") ExporterKey type) throws IOException {

        String resourcePath = "D:\\allweb\\self-git\\report-generator\\src\\main\\resources";

        Path templatePath = Path.of(resourcePath, templateFile.getOriginalFilename());
        Path importPath = Path.of(resourcePath, file.getOriginalFilename());
        templateFile.transferTo(templatePath);
        file.transferTo(importPath);

        ReportExportationProvider reportExportationProvider = reportExporterFactory.determineExporter(type);

        if (file.getOriginalFilename() != null) {
            Configuration configuration = getConfiguration(FilenameUtils.getExtension(file.getOriginalFilename()), importPath.toString(), templatePath.toString());
            byte[] bytes = reportExportationProvider.exportReport(configuration);
            ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
            ContentDisposition contentDisposition = ContentDisposition
                    .attachment()
                    .filename(UUID.randomUUID() + "." + type.getKey())
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

    private Configuration getConfiguration(String type, String importPath, String jrxmlPath) throws FileNotFoundException {

        JRFileConfigurationKey configurationKey = JRFileConfigurationKey.valueOf(type.toUpperCase(Locale.ENGLISH));

        AbstractJRConfiguration configuration = determineJrConfiguration(importPath, configurationKey);

        configuration.setJxmlTemplateCustomizer((jrxml) -> {
            JasperDesign jasperDesign = JRXmlLoader.load(jrxml);
            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setLanguage(configurationKey.getKey());

            jasperDesign.setQuery(jrDesignQuery);
            return JasperCompileManager.compileReport(jasperDesign);
        });

        configuration.addJrXml(new FileInputStream(jrxmlPath));

        return configuration;
    }

    private static AbstractJRConfiguration determineJrConfiguration(String importPath, JRFileConfigurationKey configurationKey) {

        AbstractJRConfiguration configuration;

        switch (configurationKey) {
            case CSV -> configuration = new JRCSVConfiguration(importPath);
            case JSON -> configuration = new JRJsonConfiguration(importPath);
            case XML -> configuration = new JRXmlConfiguration(importPath);
            case XLSX -> configuration = new JRXlsxConfiguration(importPath);
            default -> throw new ReportException("No such configuration key found", 404);
        }

        return configuration;
    }
}
