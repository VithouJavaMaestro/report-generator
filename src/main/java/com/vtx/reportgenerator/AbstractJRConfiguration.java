package com.vtx.reportgenerator;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class AbtractJRConfiguration implements JRConfiguration {
    private List<InputStream> jrXmlTemplates;
    private Map<String, Object> parameters;
}
