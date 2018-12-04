package com.bwx.main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class StationCache {
    public static Properties properties = new Properties();
    public static HashMap<String,String> rediskey_type = new HashMap<>();
    static {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("code2.properties"));
            // jar包内
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rediskey_type.put("","");
    }


}
