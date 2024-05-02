package com.vtx.reportgenerator.key;

public enum JRConfigurationKey implements Key {
    JR_JDBC {
        @Override
        public String getKey() {
            return "jrJdbc";
        }
    },
    JR_BEAN_COLLECTION_DATASOURCE {
        @Override
        public String getKey() {
            return "jrBeanCollectionDataSource";
        }
    }
}
