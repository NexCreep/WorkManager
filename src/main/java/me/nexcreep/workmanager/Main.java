package me.nexcreep.workmanager;

import me.nexcreep.workmanager.Database.Connector;
import me.nexcreep.workmanager.Database.Query;
import me.nexcreep.workmanager.Works.*;
import me.nexcreep.workmanager.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {
    public Logger log = new Logger(this);
    public Connector conn;
    public Query cursor;
    public MinerWork minerWork;
    public FisherWork fisherWork;
    public WoodWork woodWork;
    public ExplorerWork explorerWork;
    public NthExplorerWork nthExplorerWork;
    public FarmerWork farmerWork;

    @Override
    public void onEnable() {
        // Plugin startup logic
        log.info("Work Manager is now Enabled");
        log.info("Loading config.yml file");
        saveDefaultConfig();
        log.info("Connecting to database...");
        this.conn = new Connector(this);
        try {
            conn.connect();
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            log.error("Couldn't established a connection with Database");
            log.error(e.toString());
        }

        if (conn.isConnected()){
            log.info("Connection establish with Database");
            this.cursor = new Query(this);

            log.info("Configuring tables...");
            cursor.createTables();
            log.info("Tables configured!");

            log.info("Loading Works");
            minerWork = new MinerWork(this);
            fisherWork = new FisherWork(this);
            woodWork = new WoodWork(this);
            explorerWork = new ExplorerWork(this);
            nthExplorerWork = new NthExplorerWork(this);
            farmerWork = new FarmerWork(this);
            log.info("Work loading sequence completed!");

            log.info("Loading commands...");
            this.getCommand("join").setExecutor(new CommandJoinWork(this));
            this.getCommand("join").setTabCompleter(new TabJoinWork(this));

            this.getCommand("leave").setExecutor(new CommandLeaveWork(this));

            this.getCommand("current").setExecutor(new CommandCurrentWork(this));

            this.getCommand("finnish").setExecutor(new CommandFinnishWork(this));
            log.info("Commands loaded successfully!");

            getServer().getPluginManager().registerEvents(new Events(this), this);
        }else {
            getServer().getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try{
            conn.diconnect();
            log.info("Disconnected form Database");
        }finally {
            log.error("It can´t disconnect from database, is possible that never it get connection to a DBMS");
        }
        log.info("Work Manager is now Disabled");
    }
}
