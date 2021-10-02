package me.nexcreep.workmanager.Works;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.commands.RandomNum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class ExplorerWork {
    public String idWork = "EW";
    public String nameWork = "Explorador";
    public String action = "encontrar";
    public int maxAmount = 25;
    String colorCode = "§9";
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;
    HashMap<Integer, Material> materialsId;
    HashMap<Integer, String> materialsTrans;

    public ExplorerWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
        if (!exec.insertWork(idWork, nameWork)){
            log.error(String.format("It couldn't insert the work %s",nameWork.toUpperCase()));
        }

        materialsId = new HashMap<>();
        materialsTrans = new HashMap<>();

        materialsId.put(1, Material.ROTTEN_FLESH);
        materialsId.put(2, Material.BONE);
        materialsId.put(3, Material.GUNPOWDER);
        materialsId.put(4, Material.PHANTOM_MEMBRANE);
        materialsId.put(5, Material.ENDER_PEARL);

        materialsTrans.put(1, "Carne Podrida");
        materialsTrans.put(2, "Hueso");
        materialsTrans.put(3, "Pólvora");
        materialsTrans.put(4, "Membrana de Fantasma");
        materialsTrans.put(5, "Perla de Ender");

        random = new RandomNum();
    }

    public boolean join(Player player){
        log.debug("On join work no query");
        if (!plugin.conn.isConnected()){
            player.sendMessage("No se ha podido unir a el trabajo (Posible ERROR MySQL-2003)");
            return false;
        }
        if (!exec.hasWork(player.getUniqueId())){
            int amount = random.setAmount(maxAmount);
            int idMaterial = random.setMaterial(materialsId.size());

            if (!exec.linkplayerToWork(player.getUniqueId(), idWork, amount, idMaterial)){
                return false;
            }
            player.sendMessage(String.format("§6Has entrado como %s%s (id: %s).", colorCode, nameWork, idWork));
            player.sendMessage(String.format("§6Se te ha asignado %s §b%s §rde §b%s.", action, amount, materialsTrans.get(idMaterial)));
            return true;
        }
        return false;
    }
}
