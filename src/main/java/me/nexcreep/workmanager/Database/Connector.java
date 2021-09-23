package me.nexcreep.workmanager.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private String host = "127.0.0.1";
    private String port = "3306";
    private String db = "workermanager";
    private String user = "spigot";
    private String password = "spigot";

    private Connection connection;

    public boolean isConnected(){
        return (connection != null);
    }

    public void connect() throws ClassNotFoundException, SQLException{
        if (!isConnected()){
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + db + "?allowPublicKeyRetrieval=true" + "&useSSL=false", user, password);
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
