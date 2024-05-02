package com.vtx.reportgenerator;

import com.vtx.reportgenerator.key.Key;

public enum ReportProvider implements Key {
    JASPER_REPORT() {
        @Override
        public String getKey() {
            return "jasperReport";
        }
    }
}
