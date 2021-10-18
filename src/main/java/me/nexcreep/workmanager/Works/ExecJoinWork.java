package me.nexcreep.workmanager.Works;

import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.commands.RandomNum;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ExecJoinWork {
    RandomNum random = new RandomNum();

    public boolean join(Main plugin, Player player,  String idWork, int maxAmount, HashMap<Integer,
            Material> materialsId, HashMap<Integer, String> materialsTrans, String colorCode, String nameWork, String action){

        plugin.log.debug("On join work no query");

        if (!plugin.conn.isConnected()){
            player.sendMessage("No se ha podido unir a el trabajo (Posible ERROR MySQL-2003)");
            return false;
        }
        if (!plugin.cursor.hasWork(player.getUniqueId())){
            int amount = random.setAmount(maxAmount);
            int idMaterial = random.setMaterial(materialsId.size());

            if (!plugin.cursor.linkplayerToWork(player.getUniqueId(), idWork, amount, idMaterial)){
                return false;
            }
            player.sendMessage(String.format("§6Has entrado como %s%s (id: %s).", colorCode, nameWork, idWork));
            player.sendMessage(String.format("§6Se te ha asignado %s §b%s §rde §b%s.", action, amount, materialsTrans.get(idMaterial)));
            return true;
        }
        return false;
    }
}
