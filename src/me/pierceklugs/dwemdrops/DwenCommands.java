package me.pierceklugs.dwemdrops;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DwenCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(sender instanceof Player){

                Player player = (Player) sender;

                if(command.getName().equalsIgnoreCase("dwenitem")){

                    if(args.length == 0) {

                        player.sendMessage("§7[§7DwenDrops§7]: Incorrect usage, please give an argument.");
                        return true;

                    }else if(args.length == 1){

                        ItemStack item = DwenLegendary.Drops.get(Integer.parseInt(args[0]));
                        player.getInventory().addItem(item);
                        player.sendMessage("§7[§7DwenDrops§7]: " + item.getItemMeta().getDisplayName() + "§7 has been summoned.");

                        return true;
                    }else{

                        player.sendMessage("§7[§7DwenDrops§7]: Incorrect usage, please give an argument.");

                        return true;

                    }


                }

            }

            return false;

    }
}
