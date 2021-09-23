package me.nexcreep.workmanager;

public class Logger {

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
        System.out.println(flog);
    }

}
