package me.pierceklugs.dwemdrops;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){

        Bukkit.getPluginManager().registerEvents(new Drops(), this);
        Bukkit.getServer().getLogger().info("DwemDrops is Online!");


    }
}
