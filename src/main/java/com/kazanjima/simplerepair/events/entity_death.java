package com.kazanjima.simplerepair.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class entity_death implements Listener {

    @EventHandler
    public void onEvent(EntityDeathEvent event){
        Entity entity = event.getEntity();
        EntityType entityType = entity.getType();
        //Use the getChance to get the chance of a drop based on what the entity is.
        //Roll for drop, and if success, drop naturally where it died.
    }

    public double getChance(EntityType entityType){
        double chance = 0;
        /*
        Update the system to use the config.yml.
        Cycle through each of the entityTypes in config until you find the matching one.
         */
        return chance;
    }
}
