package com.vtx.reportgenerator.utils;

public interface CastUtils {

    @SuppressWarnings("unchecked")
    static <T> T cast(Object object) {
        return (T) object;
    }
}

