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
    public static final String idWork = "CW";
    public static final String nameWork = "Granjero";
    public static final String action = "recolectar";
    public static final int maxAmount = 128;
    public static final String colorCode = "§2";
    public static HashMap<Integer, Material> materialsId;
    public static HashMap<Integer, String> materialsTrans;
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;

    public FarmerWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
        if (!exec.insertWork(idWork, nameWork)){
            log.error(String.format("It couldn't insert the work %s",nameWork.toUpperCase()));
        }
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
