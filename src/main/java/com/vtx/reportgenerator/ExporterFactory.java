package com.vtx.reportgenerator;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

public class ExporterFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private final ExporterType exporterType;

    public ExporterFactory(ExporterType exporterType) {
        this.exporterType = exporterType;
    }

    public ReportExporter getExportReporter() {
        return applicationContext.getBean(exporterType.getValue(), ReportExporter.class);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
