package me.pierceklugs.dwemdrops;

import java.util.*;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Drops implements Listener {
    //Drops List
    public List<ItemStack> Drops = new ArrayList<ItemStack>();

    @EventHandler
    public void createDrops(PluginEnableEvent event){
        //Ether Bow
        Drops.add(new ItemStack(Material.BOW, 1));
        ItemMeta ether = Drops.get(0).getItemMeta();
        ether.setDisplayName("§e§lThe Ether");
        List<String> ether_lore = new ArrayList<String>();
        ether_lore.add("§e§lLEGENDARY");
        ether_lore.add("§eThe Ether has a 10% chance of healing upon hit.");
        ether.setLore(ether_lore);
        ether.setUnbreakable(true);
        ether.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        ether.addEnchant(Enchantment.ARROW_DAMAGE,2, false);
        ether.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, false);
        ether.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        Drops.get(0).setItemMeta(ether);

        //Axe of Dwen
        Drops.add(new ItemStack(Material.DIAMOND_AXE, 1));
        ItemMeta Dwen = Drops.get(1).getItemMeta();
        Dwen.setDisplayName("§e§lAxe of Dwen");
        List<String> dwen_lore = new ArrayList<String>();
        dwen_lore.add("§e§lLEGENDARY");
        dwen_lore.add("§eThe Axe of Dwen grants a speed boost to the holder on right-click.");
        Dwen.setLore(dwen_lore);
        Dwen.setUnbreakable(true);
        Dwen.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        Dwen.addEnchant(Enchantment.DAMAGE_ALL,4, false);
        Dwen.addEnchant(Enchantment.KNOCKBACK, 2, false);
        Dwen.addEnchant(Enchantment.DURABILITY, 5, false);
        Drops.get(1).setItemMeta(Dwen);

    }

    @EventHandler
    public void etherPower(EntityDamageByEntityEvent event){

        Entity mob = event.getEntity();
        Entity damager = event.getDamager();
        Random rand = new Random();

        if(damager instanceof Arrow){
            Arrow arrow = (Arrow) damager;
            if(arrow.getShooter() instanceof Player){
                Player player = (Player) arrow.getShooter();
                if(player.getInventory().getItemInMainHand().equals(Drops.get(0))){
                    int chance = rand.nextInt(100);
                    if(chance <= 100){
                        if((event.getFinalDamage() + player.getHealth()) >= 20.0){

                            player.setHealth(20.0);

                        }else{

                            player.setHealth(event.getFinalDamage() + player.getHealth());

                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void dwenPower(PlayerInteractEvent event){
        Player player = event.getPlayer();
        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 6000, 2);
        if(!(player.hasPotionEffect(PotionEffectType.SPEED))) {
            if (event.getItem().equals(Drops.get(1))) {

                player.addPotionEffect(speed);

            }
        }


    }


    @EventHandler
    public void onDrop(EntityDeathEvent event){
        event.getDrops().clear();
        LivingEntity entity = event.getEntity();
        Player player = event.getEntity().getKiller();

        Random rand = new Random();

        //Skeleton Drops
        if(entity instanceof Skeleton){
            Location death_mark = entity.getLocation();
            entity.getLocation().getWorld().dropItem(death_mark, new ItemStack(Material.BONE, rand.nextInt(1) + 1));
            entity.getLocation().getWorld().dropItem(death_mark, new ItemStack(Material.BONE, rand.nextInt(2) + 1));
            entity.getLocation().getWorld().dropItem(death_mark, new ItemStack(Material.ARROW, rand.nextInt(1) + 1));

            //Life Steal Bow
            int chance = rand.nextInt(10000);
            if(chance <= 1){

                death_mark.getWorld().dropItem(death_mark, Drops.get(0));

            }
        }

        if(entity instanceof Zombie){
            Zombie zombie = (Zombie) entity;
            int chance = rand.nextInt(10000);
            if(chance <= 1){

                event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), Drops.get(1));

            }



        }



    }


}
