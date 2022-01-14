package ca.bungo.manhunt.data;

import ca.bungo.manhunt.Manhunt;

import java.util.HashMap;

public class PlayerData {

    private String uuid;
    private String name;
    private boolean isHunter;
    private Manhunt manhunt;

    /**
     * Hashmap containing all the Cooldowns and their respective Names to allow for tracking
     * */
    private HashMap<String, Cooldown> cooldowns = new HashMap<String, Cooldown>();

    /**
     * Create a new instance of Player Data
     * @param manhunt - Plugin instance
     * @param UUID - UUID String of the player
     * @param Username - Username of the player
     * */
    public PlayerData(Manhunt manhunt, String UUID, String Username){
        this.manhunt = manhunt;
        this.uuid = UUID;
        this.name = Username;
    }

    /**
     * Returns the UUID of the player who this Data represents
     * @return String - UUID of the Player
     * */
    public String getUUID(){
        return this.uuid;
    }

    /**
     * Returns the Username of the player who this Data represents
     * @return String - Username of the Player
     * */
    public String getName(){
        return this.name;
    }

    /**
     * Returns if the Player is a hunter or not
     * @return boolean - True if the player is a Hunter, false otherwise
     * */
    public boolean getHunter(){
        return isHunter;
    }

    /**
     * Set if the Player is a Hunter
     * @param value - Value to change the hunter status to
     * */
    public void setHunter(boolean value){
        this.isHunter = value;
    }

    /**
     * Get a cooldown based on its Unique Name
     * @param name - Name of the Cooldown
     * @return The cooldown
     * */
    public Cooldown getCooldown(String name) {
        Cooldown cd = null;
        cd = cooldowns.get(name);
        return cd;
    }

    /**
     * Remove a cooldown based on its name
     * @param name - Name of the Cooldown to remove
     * */
    public void removeCooldown(String name) {
        cooldowns.remove(name);
    }

    /**
     * Add a cooldown to the player
     * @param name - Name of the Cooldown
     * @param cooldown - Cooldown Datatype that will be added
     * */
    public void addCooldown(String name, Cooldown cooldown) {
        cooldowns.put(name, cooldown);
    }
}
