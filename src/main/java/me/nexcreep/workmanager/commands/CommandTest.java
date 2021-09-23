package me.nexcreep.workmanager.commands;

import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Logger;
import me.nexcreep.workmanager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {
    Main plugin;
    Query exec;
    Logger log;

    public CommandTest(Main plugin){
        this.plugin = plugin;
        exec = this.plugin.cursor;
        log = this.plugin.log;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player){
            Player playerSender = (Player) sender;
            boolean result = exec.givePlayerInfo(playerSender);
            if (result) return true;
            return false;
        }
        return true;
    }

}
