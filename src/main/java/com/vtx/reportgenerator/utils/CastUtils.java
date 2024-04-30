package com.vtx.reportgenerator.utils;
@SuppressWarnings("unchecked")
public interface CastUtils {
    static <T> T cast(Object data) {
        return (T) data;
    }
}
