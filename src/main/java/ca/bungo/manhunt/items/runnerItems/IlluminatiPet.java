package ca.bungo.manhunt.items.runnerItems;

import ca.bungo.manhunt.Manhunt;
import ca.bungo.manhunt.items.CustomItem;
import com.mojang.authlib.minecraft.TelemetrySession;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IlluminatiPet extends CustomItem {


    public IlluminatiPet(Manhunt manhunt) {
        super(manhunt, Material.STICK);

        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Illuminati Pet");
        meta.setCustomModelData(1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "-Shift+Right-Click to be given random item in Minecraft! (30 Second Cooldown)");
        lore.add(ChatColor.AQUA + "-Right-Click to go Fully Invisible for 1 minute! (5 Minute Cooldown)");
        lore.add(ChatColor.YELLOW + "Note: Using 1 ability puts BOTH abilities on a Cooldown!");
        meta.setLore(lore);
        this.setItemMeta(meta);
    }

    @EventHandler
    public void onInteractedWithItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itm = player.getInventory().getItemInMainHand();
        if(itm == null || itm.getItemMeta() == null || !itm.getItemMeta().equals(this.getItemMeta()))
            return;


        if(hasCooldown(player, "Illuminati")){
            player.getWorld().playSound(player.getLocation(), "custom:illu3", 0.25f, 1.05f);
            return;
        }
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(player.isSneaking()){
                giveCooldown(player, "Illuminati", 30);
                Material[] mats = Material.values();
                List<Material> itemMats = new ArrayList<>();
                for(Material m : mats){
                    if(m.isItem())
                        itemMats.add(m);
                }
                Random rnd = new Random();
                int item = rnd.nextInt(itemMats.size()+5);
                ItemStack itemToGive;
                if(item >= itemMats.size()){
                    itemToGive = manhunt.im.getItem("Illuminati Pet");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Illuminati confirmed!"));
                    player.getLocation().getWorld().playSound(player.getLocation(), "custom:illu2", 0.5f, 1f);
                }else{
                    itemToGive = new ItemStack(itemMats.get(item));
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.YELLOW + itemToGive.getType().name().replace('_', ' ') + " confirmed!"));
                    player.getLocation().getWorld().playSound(player.getLocation(), "custom:illu1", 0.25f, 1.15f);
                }
                if(player.getInventory().firstEmpty() == -1)
                    player.getWorld().dropItemNaturally(player.getLocation(), itemToGive);
                else
                    player.getInventory().addItem(itemToGive);
            }else {
                giveCooldown(player, "Illuminati", 300);
                player.getLocation().getWorld().playSound(player.getLocation(), "custom:illu1", 0.25f, 1.05f);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 1, true, false));
            }
        }
    }
}
