package com.vtx.reportgenerator;

import com.vtx.reportgenerator.configuration.Configuration;

public interface ReportExportationProvider extends KeyMatchable {
    byte[] exportReport(Configuration configuration) throws ReportException;
}