package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JRException;

@FunctionalInterface
public interface JasperPrintCustomizer<T, R> {
    R apply(T t) throws JRException;
}
