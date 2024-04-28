package com.vtx.reportgenerator;

public interface ReportExporter<T extends Configuration> {
    void exportReport(T configuration) throws ReportException;
}
