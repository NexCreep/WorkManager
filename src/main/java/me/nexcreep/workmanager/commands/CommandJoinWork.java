package me.nexcreep.workmanager.commands;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;

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
        if (workname.equals(plugin.minerWork.nameWork)){
            boolean r = plugin.minerWork.join(player);
            log.debug(String.format("On work selecction answer %b", r));
            return r;
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

                    if (!workSelection(args[0], playerSender)){
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
