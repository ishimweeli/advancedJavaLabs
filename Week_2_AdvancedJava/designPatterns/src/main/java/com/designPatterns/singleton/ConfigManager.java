package com.designPatterns.singleton;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private static ConfigManager instance;
    private Map<String, String> config = new HashMap<>();

    private ConfigManager() {
        // Simulating loading from file/env
        config.put("DB_URL", "jdbc:mysql://localhost/mydb");
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getConfig(String key) {
        return config.get(key);
    }

    public static void main(String[] args) {
        ConfigManager cm = ConfigManager.getInstance();
        System.out.println(cm.getConfig("DB_URL"));
    }
}