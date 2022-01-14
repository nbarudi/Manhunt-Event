package ca.bungo.manhunt.data;

import ca.bungo.manhunt.Manhunt;
import org.bukkit.Bukkit;

public class Cooldown {

    private double remainingTime;
    private int task;
    private boolean isActive;

    /**
     * Create a new cooldown timer
     * @param pl - Instance of the plugin
     * @param ticks - Double value of the number of Game Ticks the cooldown lasts
     * */
    public Cooldown(Manhunt pl, double ticks) {
        remainingTime = ticks;
        isActive = true;
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, () ->{
            if(!isActive)
                return;
            if(remainingTime <= 0) {
                isActive = false;
                return;
            }
            remainingTime--;
        }, 1, 1);
    }

    /**
     * Get the remaining time in Ticks of the cooldown
     * @return double - Ticks remaining on the cooldown
     * */
    public double getRemainingTime() {
        if(isActive) {
            double ms = remainingTime * 50;
            double seconds = ms / 1000;
            return seconds;
        }
        else {
            Bukkit.getScheduler().cancelTask(task);
            return 0;
        }
    }

}
