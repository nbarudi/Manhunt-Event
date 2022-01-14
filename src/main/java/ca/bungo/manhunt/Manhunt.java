package ca.bungo.manhunt;

import ca.bungo.manhunt.cmds.TestCommand;
import ca.bungo.manhunt.data.Cooldown;
import ca.bungo.manhunt.data.PlayerData;
import ca.bungo.manhunt.items.CustomItem;
import ca.bungo.manhunt.utility.CooldownManager;
import ca.bungo.manhunt.utility.ItemManager;
import ca.bungo.manhunt.utility.PlayerManager;
import jdk.incubator.vector.VectorOperators;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Manhunt extends JavaPlugin {

    public PlayerManager pm;
    public CooldownManager cm;
    public ItemManager im;


    @Override
    public void onEnable() {

        this.pm = new PlayerManager(this);
        this.cm = new CooldownManager(this);
        this.im = new ItemManager(this);


        for(Player player : Bukkit.getOnlinePlayers()){
            logConsole("Registering " + player.getName() + "'s Data!");
            PlayerData data = pm.createPlayerData(player);
            logConsole(data.toString());
        }

        registerCommands();

    }

    private void registerCommands() {
        this.getCommand("test").setExecutor(new TestCommand());
    }


    @Override
    public void onDisable() {
        
    }

    /**Log a message to the console
     * Use '&' to denote colour codes instead of using '§' or ChatColor.blah
     * @param message The message you want to log to the console
     * */
    public void logConsole(String message){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
