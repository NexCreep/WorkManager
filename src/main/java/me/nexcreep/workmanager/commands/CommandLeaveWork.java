package me.nexcreep.workmanager.commands;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandLeaveWork implements CommandExecutor {
    Main plugin;
    Query exec;
    Logger log;
    ArrayList<String> works;

    public CommandLeaveWork(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;

        works = exec.giveWorks();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player playerSender = (Player) sender;
            if (plugin.conn.isConnected()){
                if (exec.delinkPlayerToWork(playerSender.getUniqueId())){
                    playerSender.sendMessage("Has podido abandonar el trabajo");
                    return true;
                }
                playerSender.sendMessage("§cNo tienes ningun trabajo que abandonar.");
                playerSender.sendMessage("§6Utiliza /join <trabajo> para unirte a uno");
                return true;
            }
            playerSender.sendMessage("No se ha podido abandonar el trabajo (Posible ERROR MySQL-2003)");
        }
        return true;
    }
}
