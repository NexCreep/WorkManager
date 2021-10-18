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
    public static final String idWork = "EW";
    public static final String nameWork = "Explorador";
    public static final String action = "encontrar";
    public static final int maxAmount = 25;
    public static final String colorCode = "§9";
    public static HashMap<Integer, Material> materialsId;
    public static HashMap<Integer, String> materialsTrans;
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;

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
}
