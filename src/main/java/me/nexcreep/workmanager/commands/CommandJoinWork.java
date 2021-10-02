package me.nexcreep.workmanager.commands;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandJoinWork implements CommandExecutor {
    Main plugin;
    Query exec;
    Logger log;
    ArrayList<String> works;

    public CommandJoinWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;

        works = exec.giveWorks();
    }

    public boolean workSelection(String workname, Player player){
        if (workname.equals(plugin.minerWork.nameWork.toUpperCase())){
            return plugin.minerWork.join(player);

        }else if (workname.equals(plugin.fisherWork.nameWork.toUpperCase())){
            return plugin.fisherWork.join(player);

        }else if (workname.equals("LEÑADOR")){
            return plugin.woodWork.join(player);

        }else if (workname.equals(plugin.nthExplorerWork.nameWork.toUpperCase())){
            return plugin.nthExplorerWork.join(player);

        }else if (workname.equals(plugin.farmerWork.nameWork.toUpperCase())){
            return plugin.farmerWork.join(player);

        }else if (workname.equals(plugin.explorerWork.nameWork.toUpperCase())){
            return plugin.explorerWork.join(player);

        }
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player playerSender = (Player) sender;
            if (args.length > 0){
                if (works.contains(args[0])){
                    log.debug("Pre work selection");

                    if (!workSelection(args[0].toUpperCase(), playerSender)){
                        log.debug("Post work selection NEGATIVE");
                        playerSender.sendMessage("§cYa te encuentras dentro de un trabajo.");
                        playerSender.sendMessage("§6Utiliza /leave para abandonarlo o /finish para terminarlo.");
                        playerSender.sendMessage("§5 Mira tu asignación con /current");
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
