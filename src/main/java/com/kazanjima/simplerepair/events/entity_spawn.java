package com.kazanjima.simplerepair.events;

import com.kazanjima.simplerepair.data.data_handler;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class entity_spawn implements Listener {
    data_handler dh = new data_handler();
    @EventHandler
    public void onEvent(EntitySpawnEvent event){
        Entity entity = event.getEntity();
        if(entity.fromMobSpawner()){
            //We use 0 for false, and 1 for true.
            dh.setPDCInteger(entity, "natural-spawn", 0);
        }else{
            dh.setPDCInteger(entity, "natural-spawn", 1);
        }
    }
}
