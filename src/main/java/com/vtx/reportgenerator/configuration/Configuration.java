package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.key.Key;

public interface Configuration {
    <T> T get(String key, Class<T> clazz);

    void put(String key, Object value);

    Key getKey();
}
