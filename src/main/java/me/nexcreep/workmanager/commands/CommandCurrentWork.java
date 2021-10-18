package me.nexcreep.workmanager.commands;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.Works.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class CommandCurrentWork implements CommandExecutor {
    Main plugin;
    Query exec;
    Logger log;
    String[] result;
    String wAction;
    String wName;
    String wwid;
    String wColor;
    String mName;
    String mAmount;

    public CommandCurrentWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            Player playerSender = (Player) sender;
            return getWork(playerSender);
        }
        return false;
    }

    public boolean getWork(Player playerSender){
        result = exec.getCurrentPlayerWork(playerSender.getUniqueId());
        if (result != null){//IF response is not null...
            if (MinerWork.idWork.equals(result[0])){
                wAction = MinerWork.action;
                wName = MinerWork.nameWork;
                wwid = result[0];
                wColor = MinerWork.colorCode;
                mName = MinerWork.materialsTrans.get(Integer.parseInt(result[2]));
                mAmount = result[1];

            }else if (FisherWork.idWork.equals(result[0])){
                wAction = FisherWork.action;
                wName = FisherWork.nameWork;
                wwid = result[0];
                wColor = FisherWork.colorCode;
                mName = FisherWork.materialsTrans.get(Integer.parseInt(result[2]));
                mAmount = result[1];

            }else if (FarmerWork.idWork.equals(result[0])){
                wAction = FarmerWork.action;
                wName = FarmerWork.nameWork;
                wwid = result[0];
                wColor = FarmerWork.colorCode;
                mName = FarmerWork.materialsTrans.get(Integer.parseInt(result[2]));
                mAmount = result[1];

            }else if (ExplorerWork.idWork.equals(result[0])){
                wAction = ExplorerWork.action;
                wName = ExplorerWork.nameWork;
                wwid = result[0];
                wColor = ExplorerWork.colorCode;
                mName = ExplorerWork.materialsTrans.get(Integer.parseInt(result[2]));
                mAmount = result[1];

            }else if (WoodWork.idWork.equals(result[0])){
                wAction = WoodWork.action;
                wName = WoodWork.nameWork;
                wwid = result[0];
                wColor = WoodWork.colorCode;
                mName = WoodWork.materialsTrans.get(Integer.parseInt(result[2]));
                mAmount = result[1];

            }else if (NthExplorerWork.idWork.equals(result[0])){
                log.debug("Nether");
                wAction = NthExplorerWork.action;
                wName = NthExplorerWork.nameWork;
                wwid = result[0];
                wColor = NthExplorerWork.colorCode;
                mName = NthExplorerWork.materialsTrans.get(Integer.parseInt(result[2]));
                mAmount = result[1];

            }
            //And send message to player sender
            playerSender.sendMessage(String.format("§6Actualmente estas como %s%s (id: %s).", wColor, wName, wwid));
            playerSender.sendMessage(String.format("§6Se te ha asignado %s §b%s §fde §b%s.", wAction, mAmount, mName));
            return true;
        }else {//IF NOT...
            playerSender.sendMessage("§cNo tienes ningun trabajo.");
            playerSender.sendMessage("§6Utiliza /join <trabajo> para unirte a uno");
            return true;
        }
    }
}
