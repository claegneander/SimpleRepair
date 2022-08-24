package com.kazanjima.simplerepair.events;

import com.kazanjima.simplerepair.Main;
import com.kazanjima.simplerepair.data.data_handler;
import com.kazanjima.simplerepair.items.scroll_of_repairing;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class entity_death implements Listener {

    data_handler dh = new data_handler();
    @EventHandler
    public void onEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        EntityType entityType = entity.getType();

        /*
        This method only works for our one item right now. Adding in other items will require a revision.
         */
        double baseChance = Main.getInstance().getConfig().getDouble("drops.items.scroll-of-repairing.base-chance");
        double chance = getChance(entityType);
        chance = chance * baseChance;
        double result = generateNumber(0, 10001) * 100;

        if (chance >= result) {
            Location location = entity.getLocation();
            boolean dropEnabled = Main.getInstance().getConfig().getBoolean("drops.items.scroll-of-repairing.enabled");
            if (dropEnabled) {
                boolean spawnerMobs = Main.getInstance().getConfig().getBoolean("settings.allow-spawner-mobs");
                if (spawnerMobs) {
                    entity.getLocation().getWorld().dropItemNaturally(location, scroll_of_repairing.get());
                } else {
                    if (dh.hasPDCInteger(entity, "natural-spawn")) {
                        if (dh.getPDCInteger(entity, "natural-spawn") == 1) {
                            System.out.println("Mob was not a spawner mob.");
                            entity.getLocation().getWorld().dropItemNaturally(location, scroll_of_repairing.get());
                        }
                    }
                }
            }
        }
    }

    /*
    This method should get the chance value from the config and return it for whichever EntityType we seek.
     */
    public double getChance(EntityType entityType){
        double chance = 0;
        String entityName = entityType.name().toLowerCase();
        entityName = entityName.replace('_', '-');
        for(String key : Main.getInstance().getConfig().getConfigurationSection("drops.entityType").getKeys(false)){
            if(key.equals(entityName)){
                String result = Main.getInstance().getConfig().getString("drops.entityType." + entityName);
                if(result != null) {
                    chance = Double.parseDouble(result);
                }
            }
        }
        return chance;
    }
    public double generateNumber(double min, double max){
        double result = 0;
        Random random = new Random();
        while(result >= max || result <= min){
            result = random.nextDouble();
        }
        return result;
    }
}
