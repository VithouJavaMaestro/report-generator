package com.vtx.reportgenerator.key;

public enum ExporterKey implements Key {
    HTML {
        @Override
        public String getKey() {
            return "html";
        }
    },
    DOCX {
        @Override
        public String getKey() {
            return "docx";
        }
    },
    DOC {
        @Override
        public String getKey() {
            return "doc";
        }
    },
    PDF {
        @Override
        public String getKey() {
            return "pdf";
        }
    },
    PPT {
        @Override
        public String getKey() {
            return "ppt";
        }
    },
    PPTX {
        @Override
        public String getKey() {
            return "pptx";
        }
    },
    XLSX {
        @Override
        public String getKey() {
            return "xlsx";
        }
    },
    XLS {
        @Override
        public String getKey() {
            return "xls";
        }
    }
}
