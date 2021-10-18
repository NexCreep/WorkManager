package me.nexcreep.workmanager.Database;

import me.nexcreep.workmanager.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Connector {

    private static String host;
    private static String port;
    private static String db;
    private static String user;
    private static String password;
    private static String APKR;
    private static String SSL;
    private static String jdbcUrl;

    private Connection connection;
    private Main plugin;
    private final FileConfiguration config;

    public Connector(Main plugin){
        this.plugin = plugin;

        this.plugin.reloadConfig();
        config = this.plugin.getConfig();

        host = config.getString("dbms.host");
        port = config.getString("dbms.port");
        db = config.getString("dbms.database");
        user = config.getString("dbms.user");
        password = config.getString("dbms.password");
        APKR = config.getString("dbms.allowPublicKeyRetrieval");
        SSL = config.getString("dbms.useSSL");
        jdbcUrl = config.getString("dbms.url");
    }

    public boolean isConnected(){
        return (connection != null);
    }

    public void connect() throws ClassNotFoundException, SQLException{
        if (!isConnected()){
            if (config.getBoolean("dbms.useJDBC")){
                connection = DriverManager.getConnection(jdbcUrl, user, password);
            }else {
                String link = String.format("jdbc:mysql://%s:%s/%s?useSSL=%s&allowPublicKeyRetrieval=%s", host, port, db, SSL, APKR);
                connection = DriverManager.getConnection(link, user, password);
            }
        }
    }

    public void diconnect() {
        if (isConnected()){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }

}
