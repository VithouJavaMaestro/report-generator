package com.vtx.reportgenerator.key;

public enum ProviderKey implements Key {
    JASPER_REPORT() {
        @Override
        public String getName() {
            return "jasperReport";
        }
    }
}
