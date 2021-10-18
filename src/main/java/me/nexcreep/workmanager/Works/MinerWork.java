package me.nexcreep.workmanager.Works;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.commands.RandomNum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class MinerWork {
    public static final String idWork = "MW";
    public static final String nameWork = "Minero";
    public static final String action = "minar";
    public static final int maxAmount = 128;
    public static final String colorCode = "§b";
    public static HashMap<Integer, Material> materialsId;
    public static HashMap<Integer, String> materialsTrans;
    Integer reward = 8;
    Player playerWorker;
    Inventory playerInv;
    Main plugin;
    Query exec;
    Logger log;
    RandomNum random;

    public MinerWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
        if (!exec.insertWork(idWork, nameWork)){
            log.error(String.format("It couldn't insert the work %s",nameWork.toUpperCase()));
        }

        materialsId = new HashMap<>();
        materialsTrans = new HashMap<>();

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

        random = new RandomNum();

    }

}
