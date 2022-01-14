package ca.bungo.manhunt.utility;

import ca.bungo.manhunt.Manhunt;
import ca.bungo.manhunt.data.Cooldown;
import ca.bungo.manhunt.data.PlayerData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CooldownManager {

    private Manhunt pl;


    public CooldownManager(Manhunt pl) {
        this.pl = pl;
    }

    //1 Tick = 50 milliseconds
    public Cooldown giveCooldown(Player player, double ticks) {
        giveEffect(player, (int) ticks);
        Cooldown cooldown = new Cooldown(pl, ticks);
        return cooldown;
    }

    private void giveEffect(Player player, int ticks) {
        ServerPlayer srvplr = ((CraftPlayer)player).getHandle();
        Item item = srvplr.getInventory().getItem(srvplr.getInventory().selected).getItem();
        srvplr.getCooldowns().addCooldown(item, ticks);
    }

    public boolean onCooldown(Player player, String name) {
        PlayerData data = pl.pm.getPlayerData(player);

        Cooldown cd = data.getCooldown(name);
        if(cd == null)
            return false;
        if(cd.getRemainingTime() > 0) {
            player.sendMessage(ChatColor.YELLOW + "" + cd.getRemainingTime() + ChatColor.BLUE + " seconds remaining until you can use this again...");
            return true;
        }
        data.removeCooldown(name);
        return false;
    }

    public void giveCooldown(Player player, String name, double seconds) {
        if(!onCooldown(player, name)) {
            Cooldown cd = giveCooldown(player, seconds*20);
            pl.pm.getPlayerData(player).addCooldown(name, cd);
        }
    }

}
