package me.pierceklugs.dwemdrops;

import java.util.*;


import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;


public class Drops implements Listener {

    public static List<ItemStack> Drops = DwenLegendary.Drops;


    @EventHandler
    public void onDrop(EntityDeathEvent event){
        LivingEntity entity = event.getEntity();
        Player player = event.getEntity().getKiller();

        Random rand = new Random();

        //Skeleton Drops
        if(entity instanceof Skeleton){
            Location death_mark = entity.getLocation();
            //Life Steal Bow
            int chance = rand.nextInt(10000);
            if(chance <= 1){

                death_mark.getWorld().dropItem(death_mark, Drops.get(0));

            }
        }

        //Zombie Drops
        if(entity instanceof Zombie){
            Zombie zombie = (Zombie) entity;
            int chance = rand.nextInt(10000);
            if(chance <= 1){

                event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), Drops.get(1));

            }
        }


        //TODO:Creeper Drops
        if(entity instanceof Creeper){
            Creeper creeper = (Creeper) entity;
            int chance = rand.nextInt(10000);
            if(chance <= 1){

                event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), Drops.get(2));

            }

        }

    }
}
