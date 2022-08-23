package com.kazanjima.simplerepair.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class entity_death implements Listener {

    @EventHandler
    public void onEvent(EntityDeathEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Zombie){
            //Do stuff?
        }
    }
}
