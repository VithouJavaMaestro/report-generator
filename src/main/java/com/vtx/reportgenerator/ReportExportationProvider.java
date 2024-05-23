package com.vtx.reportgenerator;

import com.vtx.reportgenerator.configuration.Configuration;
import com.vtx.reportgenerator.key.Key;

public interface ReportExportationProvider {

    byte[] exportReport(Configuration configuration) throws ReportException;

    Key getProvider();

    String getId();
}
