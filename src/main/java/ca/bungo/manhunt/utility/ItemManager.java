package ca.bungo.manhunt.utility;

import ca.bungo.manhunt.Manhunt;
import ca.bungo.manhunt.items.CustomItem;
import ca.bungo.manhunt.items.runnerItems.IlluminatiPet;

import java.util.HashMap;

public class ItemManager {

    private HashMap<String, CustomItem> items = new HashMap<>();

    Manhunt manhunt;

    public ItemManager(Manhunt manhunt){
        this.manhunt = manhunt;
        
        registerItems();
        registerEvents();
    }

    public CustomItem getItem(String name){
        return items.get(name);
    }

    private void registerItems() {
        items.put("Illuminati Pet", new IlluminatiPet(manhunt));
    }

    private void registerEvents() {
        for(CustomItem item : items.values()){
            manhunt.logConsole("Registering an Item!");
            manhunt.getServer().getPluginManager().registerEvents(item, manhunt);
        }
    }


}
