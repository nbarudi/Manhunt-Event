package ca.bungo.manhunt.cmds;

import ca.bungo.manhunt.Manhunt;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player pl = (Player) sender;
            pl.getInventory().addItem(Manhunt.getPlugin(Manhunt.class).im.getItem("Illuminati Pet"));
        }

        return false;
    }
}
