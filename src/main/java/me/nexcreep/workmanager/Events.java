package me.nexcreep.workmanager;

import me.nexcreep.workmanager.Database.Connector;
import me.nexcreep.workmanager.commands.CommandCurrentWork;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Events implements Listener {
    private Main plugin;
    Logger log;

    public Events(Main plugin){
        this.plugin = plugin;
        log = this.plugin.log;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (plugin.conn.isConnected()){
            Player p = e.getPlayer();
            boolean r = plugin.cursor.insertPlayer(p);
            if (r){
                UUID uuid = p.getUniqueId();
                log.info(String.format("Created user %s (%s)", uuid.toString(), p.getName()));
            }
            CommandCurrentWork c = new CommandCurrentWork(plugin);
            c.getWork(p);
        }
    }
}
