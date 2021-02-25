package me.pierceklugs.dwemdrops;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DwenLegendary  implements Listener {
    //Drops List
    public static List<ItemStack> Drops = new ArrayList<ItemStack>();


    @EventHandler
    public void createDrops(PluginEnableEvent event){

        //The Ether
        Drops.add(new ItemStack(Material.BOW, 1));
        ItemMeta ether = Drops.get(0).getItemMeta();
        ether.setDisplayName("§eThe Ether");
        List<String> ether_lore = new ArrayList<String>();
        ether_lore.add("§e§lLEGENDARY");
        ether_lore.add("§8The Ether has a 10% chance of healing upon hit.");
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
        Dwen.setDisplayName("§eAxe of Dwen");
        List<String> dwen_lore = new ArrayList<String>();
        dwen_lore.add("§e§lLEGENDARY");
        dwen_lore.add("§8The Axe of Dwen grants a speed boost to the holder");
        dwen_lore.add("§8on right-click.");
        Dwen.setLore(dwen_lore);
        Dwen.setUnbreakable(true);
        Dwen.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        Dwen.addEnchant(Enchantment.DAMAGE_ALL,4, false);
        Dwen.addEnchant(Enchantment.KNOCKBACK, 2, false);
        Dwen.addEnchant(Enchantment.DURABILITY, 5, false);
        Drops.get(1).setItemMeta(Dwen);

        //Xenia
        Drops.add(new ItemStack(Material.STICK, 1));
        ItemMeta xenia = Drops.get(2).getItemMeta();
        xenia.setDisplayName("§eXenia");
        List<String> xenia_lore = new ArrayList<String>();
        xenia_lore.add("§e§lLEGENDARY");
        xenia_lore.add("§8Xenia uses energy from time and space to");
        xenia_lore.add("§8teleport the user to another location.");
        xenia.setLore(dwen_lore);
        xenia.setUnbreakable(true);
        xenia.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        xenia.addEnchant(Enchantment.KNOCKBACK, 5, false);
        Drops.get(2).setItemMeta(xenia);

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
                    if(chance <= 10){
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
        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 300, 1);
        if(event.getPlayer().getInventory().getItemInMainHand().equals(Drops.get(1)) && !(player.hasPotionEffect(PotionEffectType.SPEED))){

            player.addPotionEffect(speed);

        }
    }

}
