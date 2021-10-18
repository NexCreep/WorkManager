package me.nexcreep.workmanager.commands;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.Works.*;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandJoinWork implements CommandExecutor {
    Main plugin;
    Query exec;
    Logger log;
    ArrayList<String> works;
    ExecJoinWork joinW;

    public CommandJoinWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;

        //Take work from database
        works = exec.giveWorks();

        //Initialize ExecJoinWork join
        joinW = new ExecJoinWork();
    }

    public boolean workSelection(String workname, Player player){
        if (workname.equals(MinerWork.nameWork.toUpperCase())){
            return joinW.join(plugin, player, MinerWork.idWork, MinerWork.maxAmount, MinerWork.materialsId,
                    MinerWork.materialsTrans, MinerWork.colorCode, MinerWork.nameWork, MinerWork.action);

        }else if (workname.equals(FisherWork.nameWork.toUpperCase())){
            return joinW.join(plugin, player, FisherWork.idWork, FisherWork.maxAmount, FisherWork.materialsId,
                    FisherWork.materialsTrans, FisherWork.colorCode, FisherWork.nameWork, FisherWork.action);

        }else if (workname.equals(WoodWork.nameWork.toUpperCase())){
            return joinW.join(plugin, player, WoodWork.idWork, WoodWork.maxAmount, WoodWork.materialsId,
                    WoodWork.materialsTrans, WoodWork.colorCode, WoodWork.nameWork, WoodWork.action);

        }else if (workname.equals(NthExplorerWork.nameWork.toUpperCase())){
            return joinW.join(plugin, player, NthExplorerWork.idWork, NthExplorerWork.maxAmount, NthExplorerWork.materialsId,
                    NthExplorerWork.materialsTrans, NthExplorerWork.colorCode, NthExplorerWork.nameWork, NthExplorerWork.action);

        }else if (workname.equals(FarmerWork.nameWork.toUpperCase())){
            return joinW.join(plugin, player, FarmerWork.idWork, FarmerWork.maxAmount, FarmerWork.materialsId,
                    FarmerWork.materialsTrans, FarmerWork.colorCode, FarmerWork.nameWork, FarmerWork.action);

        }else if (workname.equals(ExplorerWork.nameWork.toUpperCase())){
            return joinW.join(plugin, player, ExplorerWork.idWork, ExplorerWork.maxAmount, ExplorerWork.materialsId,
                    ExplorerWork.materialsTrans, ExplorerWork.colorCode, ExplorerWork.nameWork, ExplorerWork.action);

        }
        return false;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player){
            Player playerSender = (Player) sender;
            if (args.length > 0){
                if (works.contains(args[0])){
                    log.debug("Pre work selection");

                    if (!workSelection(args[0].toUpperCase(), playerSender)){
                        log.debug("Post work selection NEGATIVE");
                        playerSender.sendMessage("§cYa te encuentras dentro de un trabajo.");
                        playerSender.sendMessage("§6Utiliza /leave para abandonarlo o /finnish para terminarlo.");
                        playerSender.sendMessage("§5Mira tu asignación con /current");
                        return true;
                    }
                    log.debug("Post work selection POSITIVE");
                }else{
                    playerSender.sendMessage(String.format("§cNo existe el trabajo %s. Estas son las posibilidades:", args[0]));
                    playerSender.sendMessage(command.getUsage());
                }
            }else {
                playerSender.sendMessage(command.getDescription());
            }
        }
        return true;
    }

}
