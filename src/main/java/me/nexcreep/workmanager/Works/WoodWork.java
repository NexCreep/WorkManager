package me.nexcreep.workmanager.Works;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.commands.RandomNum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class WoodWork {
    public String idWork = "LW";
    public String nameWork = "Lenador";
    public String action = "talar";
    public int maxAmount = 512;
    String colorCode = "§c";
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;
    HashMap<Integer, Material> materialsId;
    HashMap<Integer, String> materialsTrans;

    public WoodWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
        if (!exec.insertWork(idWork, nameWork)){
            log.error(String.format("It couldn't insert the work %s",nameWork.toUpperCase()));
        }

        materialsId = new HashMap<>();
        materialsTrans = new HashMap<>();

        materialsId.put(1, Material.CRIMSON_STEM);
        materialsId.put(2, Material.WARPED_STEM);
        materialsId.put(3, Material.OAK_LOG);
        materialsId.put(4, Material.SPRUCE_LOG);
        materialsId.put(5, Material.BIRCH_LOG);
        materialsId.put(6, Material.JUNGLE_LOG);
        materialsId.put(7, Material.ACACIA_LOG);
        materialsId.put(8, Material.DARK_OAK_LOG);

        materialsTrans.put(1, "Tallo Carmesí");
        materialsTrans.put(2, "Tallo distorsionado");
        materialsTrans.put(3, "Tronco de Roble");
        materialsTrans.put(4, "Tronco de Abeto");
        materialsTrans.put(5, "Tronco de Abedul");
        materialsTrans.put(6, "Tronco de Jungla");
        materialsTrans.put(7, "Tronco de Acacia");
        materialsTrans.put(8, "Tronco de Roble Oscuro");

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
