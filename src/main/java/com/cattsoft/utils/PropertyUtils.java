package com.cattsoft.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 配置文件读取工具类
 * 
 * 
 */
public class PropertyUtils {
    private static Logger log = Logger.getLogger(PropertyUtils.class);

    private static Map<String, Properties> cfgs = new HashMap<String, Properties>();

    /**
     * 载入指定的配置文件
     * 
     * @param propFileName
     * @return
     * @author
     */
    public static Properties getConfig(String propFileName) {
        Properties config = getProperty(propFileName);
        if (config == null) {
            config = writeConfig(propFileName);
        }
        return config;
    }

    private synchronized static Properties writeConfig(String propFileName) {
        Properties config = null;
        config = new Properties();
        InputStream is = null;
        try {
            is = PropertyUtils.class.getClassLoader().getResourceAsStream(propFileName);
            log.debug("writeConfig propFileName:" + propFileName + " &InputStream is "
                    + ((is == null) ? "null" : "not null"));
            config.load(is);
            cfgs.put(propFileName, config);
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                log.debug(e.getMessage());
            }
            throw new RuntimeException("failed to read sys configuration file[" + propFileName
                    + "] error");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    if (log.isDebugEnabled()) {
                        log.debug(e.getMessage());
                    }
                }
            }
        }
        return config;
    }

    private static Properties getProperty(String propFileName) {
        if (cfgs.containsKey(propFileName)) {
            return (Properties) cfgs.get(propFileName);
        } else {
            return null;
        }
    }
}
