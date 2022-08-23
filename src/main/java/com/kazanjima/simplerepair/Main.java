package com.kazanjima.simplerepair;

import com.kazanjima.simplerepair.commands.item;
import com.kazanjima.simplerepair.events.player_interact;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static Logger l;
    @Override
    public void onEnable() {
        instance = this;
        l = getLogger();

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        instance = null;
        l = null;
    }
    @SuppressWarnings("All")
    public void registerCommands(){
        try {
            this.getCommand("item").setExecutor(new item());

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        l.info("Commands have been registered.");
    }
    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new player_interact(), this);
        l.info("Events have been registered.");
    }
    public static Main getInstance(){
        return instance;
    }
}
