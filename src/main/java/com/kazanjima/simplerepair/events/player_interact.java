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
                        if (utility.compare(itemStack, targetItem)) {
                            if (state == 0) {
                                dh.setPDCInteger(player, "scroll-of-repairing", 1);
                                for(int i = 0; i <= player.getInventory().getSize(); i++) {
                                    if (player.getInventory().getItem(i) != null) {
                                        ItemStack currentItem = player.getInventory().getItem(i);
                                        assert currentItem != null;
                                        int currentDurability = currentItem.getDurability();
                                        int maxDurability = currentItem.getMaxItemUseDuration();
                                        if (currentDurability > maxDurability) {
                                            currentItem.setDurability((short) maxDurability);
                                        }
                                    }
                                }
                                utility.removeItem(player, targetItem, 1);
                                player.sendMessage(ChatColor.GREEN + "Repaired: " + itemStack.getItemMeta().displayName() );
                            }else{
                                dh.setPDCInteger(player, "scroll-of-repairing", 0);
                                player.sendMessage(ChatColor.GREEN + "Right click to confirm.");
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
