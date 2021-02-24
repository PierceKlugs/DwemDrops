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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Drops implements Listener {

    public ArrayList<ItemStack> createDrops(){
        ArrayList<ItemStack> Drops = new ArrayList<ItemStack>();

        //Ether Bow

        Drops.add(new ItemStack(Material.BOW, 1));
        ItemMeta ether = Drops.get(0).getItemMeta();
        ether.setDisplayName("§l§4Ether");
        List<String> ether_lore = new ArrayList<String>();
        ether_lore.add("§l§eLEGENDARY");
        ether_lore.add("§eThe Ether has a 10% chance of healing upon hit.");
        ether.setLore(ether_lore);
        ether.setUnbreakable(true);
        ether.addEnchant(Enchantment.ARROW_DAMAGE,2, false);
        ether.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, false);
        ether.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        Drops.get(0).setItemMeta(ether);


        return Drops;
    }

    @EventHandler
    public void etherPower(EntityDamageByEntityEvent event){
        ArrayList<ItemStack> Drops = createDrops();

        Entity mob = event.getEntity();
        Entity damager = event.getDamager();
        Random rand = new Random();

        if (damager instanceof Arrow){
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
    public void onDrop(EntityDeathEvent event){
        ArrayList<ItemStack> Drops = createDrops();
        event.getDrops().clear();
        LivingEntity entity = event.getEntity();
        Player player = event.getEntity().getKiller();

        Random rand = new Random();

        //Skeleton Drops
        if(entity instanceof Skeleton){
            Location death_mark = entity.getLocation();
            entity.getLocation().getWorld().dropItem(death_mark, new ItemStack(Material.BONE, rand.nextInt(2) + 1));
            entity.getLocation().getWorld().dropItem(death_mark, new ItemStack(Material.BOW, rand.nextInt(1) + 1));

            //Life Steal Bow
            int chance = rand.nextInt(100);
            if(chance <= 100){

                death_mark.getWorld().dropItem(death_mark, Drops.get(0));

            }
        }


    }


}
