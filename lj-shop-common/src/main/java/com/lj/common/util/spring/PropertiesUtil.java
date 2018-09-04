package com.lj.common.util.spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class PropertiesUtil extends PropertyPlaceholderConfigurer{
	private static Map<String,String> properties;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props){
		super.processProperties(beanFactoryToProcess, props);
		properties = new HashMap<String,String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String valueStr = props.getProperty(keyStr);
            valueStr = StringUtils.trimToEmpty(valueStr);
            properties.put(keyStr, valueStr);
        }
	}
	public static String getString(String key) {
        return properties.get(key);
    }
}
