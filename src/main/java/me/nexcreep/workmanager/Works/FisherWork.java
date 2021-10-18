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
    public static final String idWork = "FW";
    public static final String nameWork = "Pescador";
    public static final String action = "pescar";
    public static final int maxAmount = 20;
    public static final String colorCode = "§a";
    public static HashMap<Integer, Material> materialsId;
    public static HashMap<Integer, String> materialsTrans;
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;

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

}
