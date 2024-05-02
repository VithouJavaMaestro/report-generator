package com.vtx.reportgenerator.key;

public enum JRFileConfigurationKey implements Key {
    JSON {
        @Override
        public String getKey() {
            return "JSON";
        }
    },

    XML {
        @Override
        public String getKey() {
            return "XPath";
        }
    },

    CSV {
        @Override
        public String getKey() {
            return "csv";
        }
    },

    XLSX {
        @Override
        public String getKey() {
            return "xls";
        }
    },

    XLS {
        @Override
        public String getKey() {
            return "xls";
        }
    },
}
