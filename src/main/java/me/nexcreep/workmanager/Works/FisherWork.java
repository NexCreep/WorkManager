package me.nexcreep.workmanager.Works;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.commands.RandomNum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class FisherWork {
    public String idWork = "FW";
    public String nameWork = "Pescador";
    public String action = "pescar";
    public int maxAmount = 20;
    String colorCode = "§a";
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;
    HashMap<Integer, Material> materialsId;
    HashMap<Integer, String> materialsTrans;

    public FisherWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
        if (!exec.insertWork(idWork, nameWork)){
            log.error(String.format("It couldn't insert the work %s",nameWork.toUpperCase()));
        }

        materialsId = new HashMap<>();
        materialsTrans = new HashMap<>();

        materialsId.put(1, Material.SALMON);
        materialsId.put(2, Material.COD);
        materialsId.put(3, Material.TROPICAL_FISH);
        materialsId.put(4, Material.PUFFERFISH);
        materialsId.put(5, Material.INK_SAC);
        materialsId.put(6, Material.GLOW_INK_SAC);

        materialsTrans.put(1, "Salmon");
        materialsTrans.put(2, "Bacalao");
        materialsTrans.put(3, "Pez Tropical");
        materialsTrans.put(4, "Pez Globo");
        materialsTrans.put(5, "Saco de Tinta");
        materialsTrans.put(6, "Saco de Tinta Luminoso");

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
