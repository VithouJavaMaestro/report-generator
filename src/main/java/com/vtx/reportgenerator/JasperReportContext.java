package com.vtx.reportgenerator;

import java.io.InputStream;

public interface JasperReportContext {
    InputStream getInputStream();

    Object getValue(String key);

    void addValue(String key, Object value);
}
