package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.ExporterInput;

import java.util.Collection;

public class JRBeanDataSourceConfiguration<T> extends AbstractJRDataSourceConfiguration {
    private Collection<T> beans;
    private boolean isUseFieldDescription;

    public JRBeanDataSourceConfiguration(Collection<T> data, boolean isUseFieldDescription) {
        setBeans(data);
        setUseFieldDescription(isUseFieldDescription);
    }

    public JRBeanDataSourceConfiguration() {
    }

    @Override
    public ExporterInput getExporterInput() {
        setJrDataSource(new JRBeanCollectionDataSource(beans, isUseFieldDescription));
        return super.processJRDataSource();
    }

    public Collection<T> getBeans() {
        return beans;
    }

    public void addBean(T bean) {
        this.beans.add(bean);
    }

    public void setBeans(Collection<T> beans) {
        if (beans == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        this.beans = beans;
    }

    public void setUseFieldDescription(boolean useFieldDescription) {
        isUseFieldDescription = useFieldDescription;
    }
}
