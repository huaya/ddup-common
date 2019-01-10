package com.maxlong.study.utils;

import com.netflix.archaius.DefaultConfigLoader;
import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.api.Config;
import com.netflix.archaius.api.exceptions.ConfigException;

/** a class for netflix property
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-9-10 14:24
 */
public class DynamicPropertyHelper {

    private static final String ROOTCONFIGPATH = "config/config.properties";

    private static DefaultPropertyFactory defaultPropertyFactory;

    private static Config rootConfig;

    static {
        try {
            rootConfig = DefaultConfigLoader.builder().build().newLoader().load(ROOTCONFIGPATH);
            defaultPropertyFactory = DefaultPropertyFactory.from(rootConfig);
        } catch (ConfigException e) {
            //do nothing
        }
    }

    private DynamicPropertyHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String getStringProperty(String propName){
        return defaultPropertyFactory.getProperty(propName).asString(null).get();
    }

    public static String getStringProperty(String propName, String defaultValue){
        return defaultPropertyFactory.getProperty(propName).asString(defaultValue).get();
    }

    public static Integer getIntProperty(String propName){
        return defaultPropertyFactory.getProperty(propName).asInteger(null).get();
    }

    public static Integer getIntProperty(String propName, Integer defaultValue){
        return defaultPropertyFactory.getProperty(propName).asInteger(defaultValue).get();
    }

    public static Boolean getBooleanProperty(String propName){
        return defaultPropertyFactory.getProperty(propName).asBoolean(null).get();
    }

    public static Boolean getBooleanProperty(String propName, Boolean defaultValue){
        return defaultPropertyFactory.getProperty(propName).asBoolean(defaultValue).get();
    }

    public static Byte getByteProperty(String propName){
        return defaultPropertyFactory.getProperty(propName).asByte(null).get();
    }

    public static Byte getByteProperty(String propName, Byte defaultValue){
        return defaultPropertyFactory.getProperty(propName).asByte(defaultValue).get();
    }

}
