package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.key.JRConfigurationKey;
import com.vtx.reportgenerator.key.Key;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
    protected void afterPropertiesSet() {
        setJrDataSource(new JRBeanCollectionDataSource(beans, isUseFieldDescription));
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

    @Override
    public Key getKey() {
        return JRConfigurationKey.JR_BEAN_COLLECTION_DATASOURCE;
    }
}
