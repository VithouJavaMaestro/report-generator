package com.vtx.reportgenerator.utils;

import java.util.Arrays;

import com.vtx.reportgenerator.ReportException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class FileUtils {

    private FileUtils() {
    }

    private static final Log logger = LogFactory.getLog(FileUtils.class);

    public static void checkFileExtension(String filename, String... extensions) {
        String fileExtension = FilenameUtils.getExtension(filename);

        if (Arrays.stream(extensions).noneMatch(extension -> StringUtils.equals(extension, fileExtension))) {
            if (logger.isDebugEnabled()) {
                logger.debug("File extension must be" + Arrays.toString(extensions));
            }
            throw new ReportException("File extension must be " + Arrays.toString(extensions), 500);
        }
    }
}
