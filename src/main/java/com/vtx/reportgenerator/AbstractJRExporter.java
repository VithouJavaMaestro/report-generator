package com.vtx.reportgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public abstract class AbstractJRExporter implements ReportExporter<JRConfiguration> {
    protected OutputStream outputStream;

    public AbstractJRExporter(OutputStream output) {
        this.outputStream = output;
    }

    public AbstractJRExporter(String exportPath) throws FileNotFoundException {
        this.outputStream = new FileOutputStream(exportPath);
    }

    public AbstractJRExporter(File file) throws FileNotFoundException {
        this.outputStream = new FileOutputStream(file);
    }

    public void setOutput(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
