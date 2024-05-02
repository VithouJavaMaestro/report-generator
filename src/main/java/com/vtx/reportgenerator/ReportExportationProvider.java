package com.vtx.reportgenerator;

import com.vtx.reportgenerator.configuration.Configuration;

public interface ReportExportationProvider<T extends Configuration> extends KeyMatchable {
    byte[] exportReport(T configuration) throws ReportException;
}
