package me.nexcreep.workmanager.commands;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.Works.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class CommandFinnishWork implements CommandExecutor {

    Main plugin;
    Query exec;
    Logger log;
    Inventory inv;
    PlayerInventory pInv;
    int iAmount;
    String[] result;
    String wAction;
    String wName;
    String wwid;
    String wColor;
    String mName;
    int expectedAmount;
    Material wMaterial;
    static final String[] excludeArmour = {"CHESTPLATE", "BOOTS", "LEGGINGS", "HELMET", "SHIELD"};
    int rest;
    Boolean theresSpace = false;
    public CommandFinnishWork(Main plugin) {
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            int amount = 0;
            Player playerSender = (Player) sender;
            result = exec.getCurrentPlayerWork(playerSender.getUniqueId());
            if (result != null) {
                getWork(playerSender, result);
                Inventory inv = playerSender.getInventory();
                log.debug(String.valueOf(inv.getContents().length));

                for (int i = 0; i < inv.getContents().length; i++){
                    if (inv.getContents()[i] != null){
                        if (inv.getContents()[i].getType().equals(wMaterial)) {
                            amount += inv.getContents()[i].getAmount();
                        }
                    }
                }
                log.debug(String.valueOf(amount));
                log.debug(String.valueOf(expectedAmount));
                if (amount >= expectedAmount){
                    inv.removeItem(new ItemStack(wMaterial, expectedAmount));
                    if (exec.delinkPlayerToWork(playerSender.getUniqueId())){
                        if (hasSpace(inv)){
                            inv.addItem(new ItemStack(Material.EMERALD, 8));
                            playerSender.playSound(playerSender.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3.0F, 0.5F);
                            playerSender.sendMessage("§6¡BUEN TRABAJO!");
                            playerSender.sendMessage(String.format("§6Has terminado tu trabajo de %s%s§6 con exito.", wColor, wName));
                        }else {
                            inv.addItem(new ItemStack(wMaterial, expectedAmount));
                            playerSender.sendMessage("§cNo tienes espacio en el inventario.");
                        }
                    }else {
                        inv.addItem(new ItemStack(wMaterial, expectedAmount));
                        playerSender.sendMessage("§cNo se ha podido terminar el trabajo. (INTERNAL ERROR: NoDelinkInDatabse)");
                    }
                }else {
                    playerSender.sendMessage(String.format("§cTe faltan §r%s§c de §r%s§c.", String.valueOf(expectedAmount - amount), mName));
                }
                return true;
            }
            playerSender.sendMessage("§cNo tienes ningun trabajo que terminar.");
            playerSender.sendMessage("§6Utiliza /join <trabajo> para unirte a uno");
            return true;
        }
        return false;
    }

    public boolean hasSpace(Inventory inv){
        for (int i = 0; i <= 35 ; i++){
            if (inv.getContents()[i] == null){
                theresSpace = true;
                break;
            }else if (inv.getContents()[i].getAmount() <= 56 && inv.getContents()[i].getType().equals(Material.EMERALD)){
                theresSpace = true;
                break;
            }
            theresSpace = false;
        }
        return theresSpace;
    }

    public void getWork(Player playerSender, String[] result) {
        if (result != null) {//IF response is not null...
            if (MinerWork.idWork.equals(result[0])) {
                wAction = MinerWork.action;
                wName = MinerWork.nameWork;
                wwid = result[0];
                wColor = MinerWork.colorCode;
                mName = MinerWork.materialsTrans.get(Integer.parseInt(result[2]));
                expectedAmount = Integer.parseInt(result[1]);
                wMaterial = MinerWork.materialsId.get(Integer.parseInt(result[2]));

            } else if (FisherWork.idWork.equals(result[0])) {
                wAction = FisherWork.action;
                wName = FisherWork.nameWork;
                wwid = result[0];
                wColor = FisherWork.colorCode;
                mName = FisherWork.materialsTrans.get(Integer.parseInt(result[2]));
                expectedAmount = Integer.parseInt(result[1]);
                wMaterial = FisherWork.materialsId.get(Integer.parseInt(result[2]));

            } else if (FarmerWork.idWork.equals(result[0])) {
                wAction = FarmerWork.action;
                wName = FarmerWork.nameWork;
                wwid = result[0];
                wColor = FarmerWork.colorCode;
                mName = FarmerWork.materialsTrans.get(Integer.parseInt(result[2]));
                expectedAmount = Integer.parseInt(result[1]);
                wMaterial = FarmerWork.materialsId.get(Integer.parseInt(result[2]));

            } else if (ExplorerWork.idWork.equals(result[0])) {
                wAction = ExplorerWork.action;
                wName = ExplorerWork.nameWork;
                wwid = result[0];
                wColor = ExplorerWork.colorCode;
                mName = ExplorerWork.materialsTrans.get(Integer.parseInt(result[2]));
                expectedAmount = Integer.parseInt(result[1]);
                wMaterial = ExplorerWork.materialsId.get(Integer.parseInt(result[2]));

            } else if (WoodWork.idWork.equals(result[0])) {
                wAction = WoodWork.action;
                wName = WoodWork.nameWork;
                wwid = result[0];
                wColor = WoodWork.colorCode;
                mName = WoodWork.materialsTrans.get(Integer.parseInt(result[2]));
                expectedAmount = Integer.parseInt(result[1]);
                wMaterial = WoodWork.materialsId.get(Integer.parseInt(result[2]));

            } else if (NthExplorerWork.idWork.equals(result[0])) {
                log.debug("Nether");
                wAction = NthExplorerWork.action;
                wName = NthExplorerWork.nameWork;
                wwid = result[0];
                wColor = NthExplorerWork.colorCode;
                mName = NthExplorerWork.materialsTrans.get(Integer.parseInt(result[2]));
                expectedAmount = Integer.parseInt(result[1]);
                wMaterial = NthExplorerWork.materialsId.get(Integer.parseInt(result[2]));

            }
        }
    }
}
