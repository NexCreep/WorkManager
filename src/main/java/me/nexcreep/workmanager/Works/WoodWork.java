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
    public static final String idWork = "LW";
    public static final String nameWork = "Talador";
    public static final String action = "talar";
    public static final int maxAmount = 512;
    public static final String colorCode = "§c";
    public static HashMap<Integer, Material> materialsId;
    public static HashMap<Integer, String> materialsTrans;
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;

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
}
