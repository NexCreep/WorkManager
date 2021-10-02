package me.nexcreep.workmanager.Works;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.commands.RandomNum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class FarmerWork {
    public String idWork = "CW";
    public String nameWork = "Granjero";
    public String action = "pescar";
    public int maxAmount = 128;
    String colorCode = "§2";
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;
    HashMap<Integer, Material> materialsId;
    HashMap<Integer, String> materialsTrans;

    public FarmerWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
        if (!exec.insertWork(idWork, nameWork)){
            log.error(String.format("It couldn't insert the work %s",nameWork.toUpperCase()));

            materialsId = new HashMap<>();
            materialsTrans = new HashMap<>();

            materialsId.put(1, Material.WHEAT);
            materialsId.put(2, Material.POTATO);
            materialsId.put(3, Material.CARROT);
            materialsId.put(4, Material.BEETROOT);
            materialsId.put(5, Material.SWEET_BERRIES);
            materialsId.put(6, Material.APPLE);

            materialsTrans.put(1, "Trigo");
            materialsTrans.put(2, "Patata");
            materialsTrans.put(3, "Zanahoria");
            materialsTrans.put(4, "Remolacha");
            materialsTrans.put(5, "Bayas Dulces");
            materialsTrans.put(6, "Manzana");

            random = new RandomNum();

        }
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
