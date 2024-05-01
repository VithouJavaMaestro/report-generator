package com.vtx.reportgenerator;

public enum ReportProvider implements Key {
    JASPER_REPORT() {
        @Override
        public String getKey() {
            return "jasperReport";
        }
    }
}
