package me.nexcreep.workmanager.Works;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.commands.RandomNum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class NthExplorerWork {
    public static final String idWork = "NW";
    public static final String nameWork = "Nether";
    public static final String action = "encontrar";
    public static final int maxAmount = 15;
    public static final String colorCode = "§4";
    public static HashMap<Integer, Material> materialsId;
    public static HashMap<Integer, String> materialsTrans;
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;

    public NthExplorerWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
        if (!exec.insertWork(idWork, nameWork)){
            log.error(String.format("It couldn't insert the work %s",nameWork.toUpperCase()));
        }

        materialsId = new HashMap<>();
        materialsTrans = new HashMap<>();

        materialsId.put(1, Material.GHAST_TEAR);
        materialsId.put(2, Material.NETHER_WART);
        materialsId.put(3, Material.BLAZE_ROD);
        materialsId.put(4, Material.BLAZE_POWDER);
        materialsId.put(5, Material.GLOWSTONE_DUST);
        materialsId.put(6, Material.GLOWSTONE);
        materialsId.put(7, Material.WARPED_FUNGUS);
        materialsId.put(8, Material.CRIMSON_FUNGUS);

        materialsTrans.put(1, "Lagrima de Ghast");
        materialsTrans.put(2, "Verruga del Nether");
        materialsTrans.put(3, "Vara de Blaze");
        materialsTrans.put(4, "Polvo de Blaze");
        materialsTrans.put(5, "Polvo de Piedra Luminosa");
        materialsTrans.put(6, "Piedra Luminosa");
        materialsTrans.put(7, "Hongo Distorsionado");
        materialsTrans.put(8, "Hongo Carmesí");

        random = new RandomNum();
    }

}
