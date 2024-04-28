package com.vtx.reportgenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class JasperReportContextImpl extends HashMap<String, Object> implements JasperReportContext {
    private final InputStream inputStream;

    public JasperReportContextImpl(String path) throws FileNotFoundException {
        this.inputStream = new FileInputStream(path);
    }

    public JasperReportContextImpl(File file) throws FileNotFoundException {
        this.inputStream = new FileInputStream(file);
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public Object getValue(String key) {
        if (!containsKey(key)) {
            throw new IllegalArgumentException("Key not exist");
        }
        return get(key);
    }

    @Override
    public void addValue(String key, Object value) {
        put(key, value);
    }
}
