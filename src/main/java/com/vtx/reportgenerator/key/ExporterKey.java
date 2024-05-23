package com.vtx.reportgenerator.key;

public enum ExporterKey implements Key {
    HTML {
        @Override
        public String getName() {
            return "html";
        }
    },
    DOCX {
        @Override
        public String getName() {
            return "docx";
        }
    },
    DOC {
        @Override
        public String getName() {
            return "doc";
        }
    },
    PDF {
        @Override
        public String getName() {
            return "pdf";
        }
    },
    PPT {
        @Override
        public String getName() {
            return "ppt";
        }
    },
    PPTX {
        @Override
        public String getName() {
            return "pptx";
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
    }
}
