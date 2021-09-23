package me.nexcreep.workmanager.Works;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class MinerWork {
    public String idWork = "MW";
    public String nameWork = "Minero";
    String colorCode = "§b";
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    HashMap<Integer, Material> materialsId;
    HashMap<Integer, String> materialsTrans;

    public MinerWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
        if (!exec.insertWork(idWork, nameWork)){
            log.error(String.format("It couldn't insert the work %s",nameWork.toUpperCase()));
        }
        materialsId = new HashMap<Integer, Material>();
        materialsTrans = new HashMap<Integer, String>();

        materialsId.put(1, Material.COAL);
        materialsId.put(2, Material.RAW_IRON);
        materialsId.put(3, Material.IRON_INGOT);
        materialsId.put(4, Material.RAW_COPPER);
        materialsId.put(5, Material.COPPER_INGOT);
        materialsId.put(6, Material.RAW_GOLD);
        materialsId.put(7, Material.GOLD_INGOT);
        materialsId.put(8, Material.AMETHYST_SHARD);
        materialsId.put(9, Material.LAPIS_LAZULI);
        materialsId.put(10, Material.REDSTONE);

        materialsTrans.put(1, "Carbon");
        materialsTrans.put(2, "Hierro en Bruto");
        materialsTrans.put(3, "Lingote de Hierro");
        materialsTrans.put(4, "Cobre en Bruto");
        materialsTrans.put(5, "Lingote de Cobre");
        materialsTrans.put(6, "Oro en Bruto");
        materialsTrans.put(7, "Lingote de Oro");
        materialsTrans.put(8, "Fragmento de Amatista");
        materialsTrans.put(9, "Lapis lazuli");
        materialsTrans.put(10, "Polvo de redstone");



    }

    public int setAmount(){
        int randomAmount = (int)Math.floor(Math.random()*(127+1)+32);
        return randomAmount;
    }

    public int setMaterial(){
        int randomMaterial = (int)Math.floor(Math.random()*(9+1)+1);
        return randomMaterial;
    }

    public boolean join(Player player){
        log.debug("On join work no query");
        if (!plugin.conn.isConnected()){
            player.sendMessage("No se ha podido unir a el trabajo (Posible ERROR MySQL-2003)");
            return false;
        }
            if (!exec.hasWork(player.getUniqueId())){
                int amount = setAmount();
                System.out.println(amount);
                int idMaterial = setMaterial();
                System.out.println(idMaterial);
                if (!exec.linkplayerToWork(player.getUniqueId(), idWork, amount, idMaterial)){
                    return false;
                }
                player.sendMessage(String.format("§6Has entrado como %s%s (id: %s).", colorCode, nameWork, idWork));
                player.sendMessage(String.format("§6Se te ha asignado minar §b%s §rde §b%s.", amount, materialsTrans.get(idMaterial).toString()));
                return true;
            }
        return false;
    }

}