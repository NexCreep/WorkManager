package me.nexcreep.workmanager.commands;

import me.nexcreep.workmanager.Main;
import me.nexcreep.workmanager.Works.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TabJoinWork implements TabCompleter {
    Main plugin;

    public TabJoinWork(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args){
        if (args.length == 1){
            List<String> workNames = new ArrayList<>();

            workNames.add(MinerWork.nameWork);
            workNames.add(FisherWork.nameWork);
            workNames.add(ExplorerWork.nameWork);
            workNames.add(NthExplorerWork.nameWork);
            workNames.add(FarmerWork.nameWork);
            workNames.add(WoodWork.nameWork);

            return workNames;
        }
        return null;
    }
}
