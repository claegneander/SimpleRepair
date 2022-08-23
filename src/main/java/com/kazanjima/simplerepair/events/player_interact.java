package com.kazanjima.simplerepair.events;

import com.kazanjima.simplerepair.Main;
import com.kazanjima.simplerepair.data.data_handler;
import com.kazanjima.simplerepair.items.scroll_of_repairing;
import com.kazanjima.simplerepair.misc.utility;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class player_interact implements Listener {
    private final Main instance = Main.getInstance();
    private final data_handler dh = new data_handler();
    @EventHandler
    public void onRepairGem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {
                ItemStack itemStack = player.getItemInHand();
                ItemStack targetItem = scroll_of_repairing.get();
                int currentAmount = itemStack.getAmount();
                targetItem.setAmount(currentAmount);
                if (dh.hasPDCInteger(player, "scroll-of-repairing")) {
                    try {
                        Integer state = dh.getPDCInteger(player, "scroll-of-repairing");
                        if (state == 0) {
                            if (!utility.compare(itemStack, targetItem)) {
                                int currentDurability = itemStack.getDurability();
                                int maxDurability = player.getActiveItem().getMaxItemUseDuration();
                                if (currentDurability != maxDurability) {
                                    player.getItemInHand().setDurability((short) maxDurability);
                                    dh.setPDCInteger(player, "scroll-of-repairing", 1);
                                    utility.removeItem(player, targetItem, 1);
                                    player.sendMessage(ChatColor.GREEN + "Repair successful.");
                                }
                            }
                        }else {
                            if (utility.compare(itemStack, targetItem)) {
                                dh.setPDCInteger(player, "scroll-of-repairing", 0);
                                player.sendMessage(ChatColor.GREEN + "Right click with a damaged item.");
                            }
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }else{
                    dh.setPDCInteger(player, "scroll-of-repairing", 1);
                }
            }
        }
    }
}
