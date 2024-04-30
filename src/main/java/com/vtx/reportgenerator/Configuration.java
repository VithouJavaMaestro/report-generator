package com.vtx.reportgenerator;

public interface Configuration {
    <T> T get(String key, Class<T> clazz);

    void put(String key, Object value);
}
