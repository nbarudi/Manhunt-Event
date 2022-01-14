package ca.bungo.manhunt.items;

import ca.bungo.manhunt.Manhunt;
import ca.bungo.manhunt.data.Cooldown;
import ca.bungo.manhunt.data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public abstract class CustomItem extends ItemStack implements Listener {

    public Manhunt manhunt;

    public CustomItem(Manhunt manhunt, Material material){
        super(material);
        this.manhunt = manhunt;
    }


    public boolean hasCooldown(Player player, String name){
        PlayerData data = manhunt.pm.createPlayerData(player);

        Cooldown cd = data.getCooldown(name);
        if(cd == null)
            return false;
        if(cd.getRemainingTime() > 0) {
            double remTime = cd.getRemainingTime();
            int minutes = 0;
            int seconds = 0;

            if(remTime/60d > 1){
                minutes = (int)(remTime/60);
                seconds = (int)(remTime-(minutes*60));
            }
            if(minutes > 0)
                player.sendMessage(ChatColor.YELLOW + "" + minutes + ChatColor.BLUE + " minutes and " + ChatColor.YELLOW + seconds + ChatColor.BLUE + " seconds remaining until you can use this again...");
            else
                player.sendMessage(ChatColor.YELLOW + "" + remTime + ChatColor.BLUE + " seconds remaining until you can use this again...");
            return true;
        }
        data.removeCooldown(name);
        return false;
    }

    public void giveCooldown(Player player, String name, double seconds) {
        if(!hasCooldown(player, name)) {
            Cooldown cd = manhunt.cm.giveCooldown(player, seconds*20);
            manhunt.pm.createPlayerData(player).addCooldown(name, cd);
        }
    }
}
