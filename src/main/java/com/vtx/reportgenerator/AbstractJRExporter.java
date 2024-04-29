package com.vtx.reportgenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractJRExporter implements ReportExporter<JRConfiguration> {

    protected BufferedOutputStream outputStream;
    protected final Log logger = LogFactory.getLog(AbstractJRExporter.class);

    protected AbstractJRExporter(String exportPath) {
        this(new File(exportPath));
    }

    protected AbstractJRExporter(File file) {
        FileUtils.checkFileExtension(file.getName(), getExtension());
        try {
            this.outputStream = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException exception) {
            if (logger.isDebugEnabled()) {
                logger.debug("export path not found");
            }
            throw new ReportException("export path not found", 404);
        }
    }

    protected abstract String getExtension();
}
