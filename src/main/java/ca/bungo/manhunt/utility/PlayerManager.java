package ca.bungo.manhunt.utility;

import ca.bungo.manhunt.Manhunt;
import ca.bungo.manhunt.data.PlayerData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    Manhunt manhunt;

    public List<PlayerData> playerData;

    public PlayerManager(Manhunt manhunt) {
        this.manhunt = manhunt;
        playerData = new ArrayList<PlayerData>();
    }

    public PlayerData createPlayerData(Player player) {

        if(getPlayerData(player) != null)
            return getPlayerData(player);
        String uuid = player.getUniqueId().toString();

        PlayerData data = new PlayerData(manhunt, uuid, player.getName());
        playerData.add(data);
        return data;
    }


    public void removePlayerData(PlayerData data) {
        playerData.remove(data);
    }

    public PlayerData getPlayerData(Player player) {
        PlayerData pData = null;

        for(PlayerData data : playerData) {
            if(data.getUUID().equals(player.getUniqueId().toString()))
                pData = data;
        }

        return pData;
    }

}
