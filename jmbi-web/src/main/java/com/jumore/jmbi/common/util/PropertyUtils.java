/*
 * Copyright © 2013 Changsha jumore Network Technology Co., Ltd. All rights
 * reserved.
 * 
 * http://www.jumore.com
 */

package com.jumore.jmbi.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.jumore.dove.common.log.LogHelper;

/**
 * 属性工具类
 * 
 * @author Herbert
 * 
 */
public class PropertyUtils extends PropertyPlaceholderConfigurer {

    public static final LogHelper      Log_Helper = LogHelper.getLogger(PropertyUtils.class);

    private static Map<String, String> propertyMap;

    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propertyMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }
    }

    /**
     * getValue.
     * 
     * @author Administrator
     * @date 2017年6月5日 上午9:24:55
     * @param name
     * @return
     */
    public static String getValue(String name) {
        String value = propertyMap.get(name);
        if (StringUtils.isBlank(value)) {
            return "";
        } else {
            return value;
        }
    }

    /**
     * 不带/
     * 
     * @return
     */
    public static String getRoot() {
        String rootKey = "jmbi-web.root";
        String cmsRoot = System.getProperty(rootKey);
        if (cmsRoot.endsWith(java.io.File.separatorChar + "")) {
            cmsRoot = cmsRoot.substring(0, cmsRoot.length() - 1);
        }
        Log_Helper.getBuilder().info(cmsRoot);
        return cmsRoot;
    }
}
