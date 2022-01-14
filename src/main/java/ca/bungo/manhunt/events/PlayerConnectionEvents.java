package ca.bungo.manhunt.events;

import ca.bungo.manhunt.Manhunt;
import ca.bungo.manhunt.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectionEvents implements Listener {

    Manhunt manhunt;

    public PlayerConnectionEvents(Manhunt manhunt){
        this.manhunt = manhunt;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        manhunt.pm.createPlayerData(player);
    }
}
