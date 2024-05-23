package com.vtx.reportgenerator.key;

public enum JRFileConfigurationKey implements Key {
    JSON {
        @Override
        public String getName() {
            return "JSON";
        }
    },

    XML {
        @Override
        public String getName() {
            return "XPath";
        }
    },

    CSV {
        @Override
        public String getName() {
            return "csv";
        }
    },

    XLSX {
        @Override
        public String getName() {
            return "xlsx";
        }
    },

    XLS {
        @Override
        public String getName() {
            return "xls";
        }
    },
}
