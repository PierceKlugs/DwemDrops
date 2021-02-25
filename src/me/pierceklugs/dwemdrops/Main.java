package me.pierceklugs.dwemdrops;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){
        //Events
        Bukkit.getPluginManager().registerEvents(new Drops(), this);

        //Commands
        this.getCommand("dwenitem").setExecutor(new DwenCommands());

        Bukkit.getServer().getLogger().info("DwemDrops is Online!");

    }
    @Override
    public void onDisable() {


        Bukkit.getServer().getLogger().info("DwemDrops is Offline!");

    }
}
