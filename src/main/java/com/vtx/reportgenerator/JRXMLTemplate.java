package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.engine.type.SectionTypeEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.type.WhenResourceMissingTypeEnum;

import java.io.InputStream;

public class JRXMLTemplate {
    private InputStream jrXml;
    private WhenNoDataTypeEnum whenNoDataTypeEnum = WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL;
    private SectionTypeEnum sectionTypeEnum = SectionTypeEnum.BAND;
    private JasperReportsContext jasperReportsContext = new SimpleJasperReportsContext();
    private WhenResourceMissingTypeEnum resourceMissingTypeEnum = WhenResourceMissingTypeEnum.ERROR;

    public InputStream getJrXml() {
        return jrXml;
    }

    public void setJrXml(InputStream jrXml) {
        this.jrXml = jrXml;
    }

    public WhenNoDataTypeEnum getWhenNoDataTypeEnum() {
        return whenNoDataTypeEnum;
    }

    public void setWhenNoDataTypeEnum(WhenNoDataTypeEnum whenNoDataTypeEnum) {
        this.whenNoDataTypeEnum = whenNoDataTypeEnum;
    }

    public SectionTypeEnum getSectionTypeEnum() {
        return sectionTypeEnum;
    }

    public void setSectionTypeEnum(SectionTypeEnum sectionTypeEnum) {
        this.sectionTypeEnum = sectionTypeEnum;
    }

    public JasperReportsContext getJasperReportsContext() {
        return jasperReportsContext;
    }

    public void setJasperReportsContext(JasperReportsContext jasperReportsContext) {
        this.jasperReportsContext = jasperReportsContext;
    }

    public WhenResourceMissingTypeEnum getResourceMissingTypeEnum() {
        return resourceMissingTypeEnum;
    }

    public void setResourceMissingTypeEnum(WhenResourceMissingTypeEnum resourceMissingTypeEnum) {
        this.resourceMissingTypeEnum = resourceMissingTypeEnum;
    }
}
