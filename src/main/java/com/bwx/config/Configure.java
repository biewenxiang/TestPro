package com.bwx.config;

import java.io.*;
import java.util.Properties;

public class Configure {
    private static Configure configure = null;
    private Properties properties;

    private Configure() {
        InputStreamReader in = null;
        try {
            properties = new Properties();
            // jar包外
            in = new InputStreamReader(new FileInputStream("config.properties"), "UTF-8");
        } catch (FileNotFoundException fe) {
            // jar包内
            try {
                in = new InputStreamReader(Configure.class.getResourceAsStream("/config.properties"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                properties.load(in);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Configure(String configFile) {
        try {
            properties = new Properties();
            InputStreamReader in = new InputStreamReader(Configure.class.getResourceAsStream(configFile), "UTF-8");
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Configure getInstance() {
        if (configure == null)
            configure = new Configure();
        return configure;
    }

    public static Configure getInstance(String confFile) {
        if (configure == null)
            configure = new Configure(confFile);
        return configure;
    }

    public String getString(String key) {
        return properties.getProperty(key, "");
    }

    public Integer getInt(String key) {
        return Integer.valueOf(Integer.parseInt(properties.getProperty(key, "")));
    }

    public Double getDouble(String key) {
        return Double.valueOf(Double.parseDouble(properties.getProperty(key, "")));
    }

    public Float getFloat(String key) {
        return Float.valueOf(Float.parseFloat(properties.getProperty(key, "")));
    }

    public long getLong(String key) {
        return Long.parseLong(properties.getProperty(key, ""));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key, "false"));
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    /*
     * set
     */
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public static void main(String[] args){

        String aa = Configure.getInstance().getString("kafka.host");
        String zookeepers = Configure.getInstance().getString("zookeeper.hosts");

        System.out.println(aa+zookeepers);
    }


}
