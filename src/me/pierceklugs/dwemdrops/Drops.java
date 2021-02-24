package me.pierceklugs.dwemdrops;

import java.util.*;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Drops implements Listener {

    public ArrayList<ItemStack> createDrops(){
        ArrayList<ItemStack> Drops = new ArrayList<ItemStack>();

        //Ether Bow
        Drops.add(new ItemStack(Material.BOW, 1));
        ItemMeta ether = Drops.get(0).getItemMeta();
        ether.setDisplayName("Ether");
        List<String> ether_lore = new ArrayList<String>();
        ether_lore.add("LEGENDARY");
        ether_lore.add("The Ether has a 10% chance of healing upon hit.");
        ether.setLore(ether_lore);
        ether.setUnbreakable(true);
        ether.addEnchant(Enchantment.ARROW_DAMAGE,2, true);
        ether.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
        ether.addEnchant(Enchantment.ARROW_INFINITE, 1, true);


        return Drops;
    }

    @EventHandler
    public void etherPower(EntityDamageByEntityEvent event){
        ArrayList<ItemStack> Drops = createDrops();

        Entity mob = event.getEntity();
        Entity damager = event.getDamager();
        Random rand = new Random();

        if(damager instanceof Player){
            Player player = (Player) damager;
            if(player.getMainHand().equals(Drops.get(0))){

                int chance = rand.nextInt(100);
                if(chance <= 100){

                    double life = event.getDamage();
                    player.setHealth(player.getHealth() + life);

                }

            }
        }


    }

    @EventHandler
    public void onDrop(EntityDeathEvent event){
        ArrayList<ItemStack> Drops = createDrops();
        event.getDrops().clear();
        LivingEntity entity = event.getEntity();
        Player player = event.getEntity().getKiller();

        Random rand = new Random();

        //Skeleton Drops
        if(entity instanceof Skeleton){
            Location death_mark = entity.getLocation();
            entity.getLocation().getWorld().dropItem(death_mark, new ItemStack(Material.BONE, rand.nextInt(3)));
            entity.getLocation().getWorld().dropItem(death_mark, new ItemStack(Material.BOW, rand.nextInt(2)));

            //Life Steal Bow
            int chance = rand.nextInt(100);
            if(chance <= 100){

                entity.getLocation().getWorld().dropItem(death_mark, Drops.get(0));

            }
        }


    }


}
