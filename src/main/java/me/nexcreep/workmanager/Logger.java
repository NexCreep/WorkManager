package me.nexcreep.workmanager;

import org.bukkit.configuration.file.FileConfiguration;

public class Logger {
    FileConfiguration config;

    public Logger(Main plugin){
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    private static final String plugin = "Work Manager";

    public void info(String text){
        String flog = String.format("[%s/INFO] %s",plugin ,text);
        System.out.println(flog);
    }
    public void error(String text){
        String flog = String.format("[%s/ERROR] %s",plugin ,text);
        System.out.println(flog);
    }
    public void debug(String text){
        String flog = String.format("[%s/DEBUG] %s",plugin ,text);
        if (config.getBoolean("Debug-sout")){
            System.out.println(flog);
        }
    }

}
