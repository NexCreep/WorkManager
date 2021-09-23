package me.nexcreep.workmanager.Database;

import me.nexcreep.workmanager.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Query {
    private Main plugin;

    public Query(Main plugin){
        this.plugin = plugin;
    }

    public void createTables(){
        PreparedStatement playersTable;
        PreparedStatement playerWork;
        PreparedStatement worksTable;
        try{
            playersTable = plugin.conn.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS players" +
                            "(uuid VARCHAR(100), name VARCHAR(100), hasworked BIT(1),PRIMARY KEY (uuid))");
            playersTable.executeUpdate();

            playerWork = plugin.conn.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS playerwork" +
                            "(uuid VARCHAR(100), wwid VARCHAR(100), amount INT, material INT, PRIMARY KEY (uuid))");
            playerWork.executeUpdate();

            worksTable = plugin.conn.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS works" +
                            "(wwid VARCHAR(100), name VARCHAR(100), PRIMARY KEY (wwid))");
            worksTable.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
    public boolean insertPlayer(Player p){
        try{
            UUID uuid = p.getUniqueId();
            if (!existsPlayer(uuid)){
                PreparedStatement insertP = plugin.conn.getConnection().prepareStatement(
                        "INSERT IGNORE INTO players" + "(uuid, name, hasworked) VALUES (?, ?, 0)");
                insertP.setString(1, uuid.toString());
                insertP.setString(2, p.getName());
                insertP.executeUpdate();

                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertWork(String wwid, String nameWork){
        try{
            if (!existsWork(wwid)){
                PreparedStatement insertW = plugin.conn.getConnection().prepareStatement(
                        "INSERT IGNORE INTO works" + "(wwid, name) VALUES (?, ?)");
                insertW.setString(1, wwid);
                insertW.setString(2, nameWork);
                insertW.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsPlayer(UUID uuid){
        try {
            PreparedStatement selectUuid = plugin.conn.getConnection().prepareStatement("SELECT * FROM players WHERE uuid=?");
            selectUuid.setString(1, uuid.toString());
            ResultSet r = selectUuid.executeQuery();
            if (r.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean givePlayerInfo(Player player){
        try{
            PreparedStatement selectAll = plugin.conn.getConnection().prepareStatement("SELECT * FROM players");
            ResultSet r = selectAll.executeQuery();
            String[] dbQuery = new String[0];
            while (r.next()){
                dbQuery = new String[]{r.getString("uuid"),
                r.getString("name"), String.valueOf(r.getByte("hasworked"))};
            }
            player.sendMessage(dbQuery);
            for (int e=0 ; e < dbQuery.length; e++){
                System.out.println(e);
                System.out.println(dbQuery[e]);
            };
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> giveWorks(){
        try{
            PreparedStatement selectWork = plugin.conn.getConnection().prepareStatement("SELECT * FROM works;");
            ResultSet r = selectWork.executeQuery();

            int rSize = 0;
            if (r != null){
                r.last();
                rSize = r.getRow();
                r.beforeFirst();
            }

            ArrayList<String> dbQuery = new ArrayList<String>(rSize);

            while (r.next()){
                dbQuery.add(r.getString("name"));
            }

            return dbQuery;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<String>(0);
    }

    public boolean existsWork(String wwid){
        try {
            PreparedStatement selectWwid = plugin.conn.getConnection().prepareStatement("SELECT * FROM works WHERE wwid=?");
            selectWwid.setString(1, wwid);
            ResultSet r = selectWwid.executeQuery();
            if (r.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasWork(UUID uuid){
        plugin.log.debug("Has work?");
        try {
            PreparedStatement hasWork = plugin.conn.getConnection().prepareStatement("SELECT * FROM playerwork WHERE uuid=?");
            hasWork.setString(1, uuid.toString());
            ResultSet r = hasWork.executeQuery();
            if (r.next()){
                plugin.log.debug("True");
                return true;
            }
            plugin.log.debug("False");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean linkplayerToWork(UUID uuid, String wwid, int amount, int material){
        if (!hasWork(uuid)){
            plugin.log.debug("On join work query");
            try{
                PreparedStatement joinWork = plugin.conn.getConnection().prepareStatement("INSERT INTO playerwork" +
                        "(uuid, wwid, amount, material) VALUES (?, ?, ?, ?)");

                joinWork.setString(1, uuid.toString());
                joinWork.setString(2, wwid);
                joinWork.setInt(3, amount);
                joinWork.setInt(4, material);

                joinWork.executeUpdate();

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean delinkPlayerToWork(UUID uuid){
        if (hasWork(uuid)){
            plugin.log.debug("On leave work query");
            try{
                PreparedStatement leaveWork = plugin.conn.getConnection().prepareStatement("DELETE FROM playerwork " +
                        "WHERE uuid=?");

                leaveWork.setString(1 ,uuid.toString());

                leaveWork.executeUpdate();

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}