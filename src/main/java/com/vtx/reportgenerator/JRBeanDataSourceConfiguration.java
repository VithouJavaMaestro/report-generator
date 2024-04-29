package com.vtx.reportgenerator;

import java.util.Collection;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.ExporterInput;

public class JRBeanDataSourceConfiguration<T> extends AbstractJRDataSourceConfiguration {

    private Collection<T> beans;
    private boolean isUseFieldDescription;

    public JRBeanDataSourceConfiguration(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        setJrDataSource(new JRBeanCollectionDataSource(data, isUseFieldDescription));
        this.beans = data;
    }

    @Override
    public ExporterInput getExporterInput() {
        return super.processJRDataSource();
    }

    public Collection<T> getBeans() {
        return beans;
    }


    public void setBeans(Collection<T> data) {
        this.beans = data;
    }

    public boolean isUseFieldDescription() {
        return isUseFieldDescription;
    }

    public void setUseFieldDescription(boolean useFieldDescription) {
        isUseFieldDescription = useFieldDescription;
    }
}
